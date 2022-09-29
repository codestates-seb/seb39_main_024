package com.codestates.flyaway.domain.member.service;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import com.codestates.flyaway.domain.memberimage.MemberImage;
import com.codestates.flyaway.domain.memberimage.service.MemberImageService;
import com.codestates.flyaway.domain.record.entity.Record;
import com.codestates.flyaway.domain.record.repository.RecordRepository;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.util.Optional;

import static com.codestates.flyaway.global.exception.ExceptionCode.*;
import static com.codestates.flyaway.web.member.dto.MemberDto.*;
import static com.codestates.flyaway.web.member.dto.MemberDto.JoinResponseDto.toJoinResponse;
import static com.codestates.flyaway.web.member.dto.MemberDto.MemberProfileResponseDto.*;
import static com.codestates.flyaway.web.member.dto.MemberDto.UpdateResponseDto.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberImageService memberImageService;
    private final RecordRepository recordRepository;


    /**
     * 회원가입
     * @return 가입 완료된 회원의 id, name, email, createdAt
     */
    public JoinResponseDto join(JoinRequestDto joinRequestDto) {

        verifyEmail(joinRequestDto.getEmail());

        Member member = joinRequestDto.toEntity();
        Member savedMember = memberRepository.save(member);

        return toJoinResponse(savedMember);
    }

    /**
     * 회원 정보 수정
     * @return 수정 완료된 회원의 id, name, email, modifiedAt
     */
    public UpdateResponseDto update(UpdateRequestDto updateRequestDto) {

        String name = updateRequestDto.getName();
        String password = updateRequestDto.getPassword();

        Member member = findById(updateRequestDto.getMemberId());
        member.update(name, password);

        saveImage(updateRequestDto, member);
        return toUpdateResponse(member);
    }

    /**
     * 회원 프로필 (특정 회원을 모든 운동기록과 함께 조회)
     * @return 회원 프로필 정보
     */
    @Transactional(readOnly = true)
    public MemberProfileResponseDto findByIdFetch(long memberId) { //todo : 테스트 코드에서의 오류 해결

        Member findMember = memberRepository.findByIdFetch(memberId)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));

        //회원의 누적 운동 기록
        long totalRecord = recordRepository.findByMemberId(memberId)
                .stream()
                .mapToLong(Record::getRecord)
                .sum();

        return toProfileResponse(findMember, totalRecord);
    }

    /**
     * 회원 탈퇴
     */
    public void delete(long memberId) {
        Member member = findById(memberId);
        memberRepository.delete(member);
    }


    /**
     * 단순 조회 재사용 메서드
     */
    @Transactional(readOnly = true)
    public Member findById(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
    }

    /**
     * Email 검증
     */
    @Transactional(readOnly = true)
    public void verifyEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new BusinessLogicException(EMAIL_ALREADY_EXISTS);
        }
    }

    /**
     * 이미지 저장 메서드
     */
    private void saveImage(UpdateRequestDto updateRequestDto, Member member) {

        if (updateRequestDto.getImage().isEmpty()) { //todo : 이미지가 첨부된 유스 케이스 처리 분리 고려
            return;
        }
        Optional.ofNullable(member.getMemberImage())
                .ifPresent(memberImageService::delete);

        MemberImage memberImage = memberImageService.save(updateRequestDto.getImage());
        memberImage.setMember(member);
    }

    /**
     * 프로필 이미지 V1
     * @return 이미지 경로
     */
    @Transactional(readOnly = true)
    public Resource getImage(long memberId) {

        Member member = findById(memberId);
        MemberImage image = Optional.ofNullable(member.getMemberImage()).
                orElseThrow(() -> new BusinessLogicException(IMAGE_NOT_FOUND));

        String path = "file:" + memberImageService.getFullPath(image.getFileName());

        try {
            return new UrlResource(path);
        } catch (MalformedURLException e) {
            log.info("exception = {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
