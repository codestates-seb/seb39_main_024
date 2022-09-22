package com.codestates.flyaway.domain.member.service;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import com.codestates.flyaway.domain.memberimage.MemberImage;
import com.codestates.flyaway.domain.memberimage.service.MemberImageService;
import com.codestates.flyaway.domain.record.entity.Record;
import com.codestates.flyaway.domain.record.repository.RecordRepository;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static com.codestates.flyaway.global.exception.ExceptionCode.*;
import static com.codestates.flyaway.web.auth.dto.AuthDto.*;
import static com.codestates.flyaway.web.auth.dto.AuthDto.JoinResponseDto.*;
import static com.codestates.flyaway.web.member.dto.MemberDto.*;
import static com.codestates.flyaway.web.member.dto.MemberDto.MemberProfileResponseDto.*;
import static com.codestates.flyaway.web.member.dto.MemberDto.UpdateResponseDto.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberImageService memberImageService;
    private final RecordRepository recordRepository;


    /**
     * 회원가입
     * @return 가입 완료된 회원의 id, name, email, createdAt
     */
    public JoinResponseDto join(JoinRequestDto joinRequestDto){

        verifyEmail(joinRequestDto.getEmail());
        joinRequestDto.setPassword(encode(joinRequestDto.getPassword()));

        Member member = joinRequestDto.toEntity();
        Member savedMember = memberRepository.save(member);

        return toJoinResponse(savedMember);
    }

    /**
     * 회원 정보 수정
     * @return 수정 완료된 회원의 id, name, email, modifiedAt
     */
     public UpdateResponseDto update(UpdateRequestDto updateRequestDto) throws IOException {

         String name = updateRequestDto.getName();
         String password = updateRequestDto.getPassword();

         Optional.ofNullable(password)
                 .ifPresent(pw -> updateRequestDto.setPassword(encode(pw)));

         Member member = findById(updateRequestDto.getMemberId());
         member.update(name, password);

         //이미지를 첨부했을 경우
         if (!updateRequestDto.getImage().isEmpty()) {
             //기존에 이미지가 있을 경우 삭제
             if (member.getMemberImage() != null) {
                 memberImageService.deleteImage(member.getMemberImage());   /////삭제 시 pk 오류
             }
             MemberImage memberImage = memberImageService.saveImage(updateRequestDto.getImage());
             memberImage.setMember(member);
         }

         return toUpdateResponse(member);
     }

    /**
     * 회원 프로필 (특정 회원을 모든 운동기록과 함께 조회)
     * @return 회원 프로필 정보
     */
    @Transactional(readOnly = true)
    public MemberProfileResponseDto findByIdFetch(long memberId) { //테스트 코드에서 오류

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
        memberRepository.findByEmail(email)
                .ifPresent(m -> {throw new BusinessLogicException(EMAIL_ALREADY_EXISTS);});
    }

    /**
     * Password 암호화
     */
    public static String encode(String password) {  //시간되면 salt 적용

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes());
            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 프로필 사진 V1
     */
    public String getImage(long memberId) {   ///리팩토링 (메서드 위치, 의미없는 +1 쿼리)

        Member member = findById(memberId);
        MemberImage image = Optional.ofNullable(member.getMemberImage()).
                orElseThrow(() -> new BusinessLogicException(IMAGE_NOT_FOUND));

        return "file:" + memberImageService.getFullPath(image.getFileName());
    }
}