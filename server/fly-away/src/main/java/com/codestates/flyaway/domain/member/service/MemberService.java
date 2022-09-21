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
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

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

    private static final String REG_EMAIL = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
    private static final String REG_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,}$";  //비번 최소 8글자, 글자, 숫자, 특수문자 1개


    /**
     * 회원가입
     * @return 가입 완료된 회원의 id, name, email, createdAt
     */
    public JoinResponseDto join(JoinRequestDto joinRequestDto) throws IOException {

        verifyEmail(joinRequestDto.getEmail());
        verifyPassword(joinRequestDto.getPassword());
        joinRequestDto.setPassword(encode(joinRequestDto.getPassword()));   //암호화 위치, 방법 리팩토링

        Member member = joinRequestDto.toEntity();

        //이미지를 첨부했을 경우
        if (joinRequestDto.getImage() != null) {
            MemberImage memberImage = memberImageService.saveImage(joinRequestDto.getImage());
            memberImage.setMember(member);
        }

        Member savedMember = memberRepository.save(member);
        return toJoinResponse(savedMember);
    }

    /**
     * 회원 정보 수정
     * @return 수정 완료된 회원의 id, name, email
     */
     public UpdateResponseDto update(UpdateRequestDto updateRequestDto) throws IOException {

         String name = updateRequestDto.getName();
         String password = updateRequestDto.getPassword();

         if (password != null) {
             verifyPassword(password);
         }

         Member member = findById(updateRequestDto.getMemberId());
         member.update(name, password);

         //이미지를 첨부했을 경우
         if (updateRequestDto.getImage() != null) {
             memberImageService.deleteImage(member.getMemberImage());
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
    public MemberProfileResponseDto findByIdFetch(long memberId) {

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
     * 회원 목록 조회
     */
    public List<Member> findAllMembers() {   ////////
        return memberRepository.findAll();
    }

    /**
     * 회원 탈퇴
     */
    public void delete(long memberId) {
        memberRepository.deleteById(memberId);
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

        if (!Pattern.matches(REG_EMAIL, email)) {
            throw new BusinessLogicException(EMAIL_NOT_VALID);
        }

        memberRepository.findByEmail(email)
                .ifPresent(m -> {throw new BusinessLogicException(EMAIL_ALREADY_EXISTS);});
    }

    /**
     * Password 검증
     */
    public void verifyPassword(String password) {
        if (!Pattern.matches(REG_PASSWORD, password)) {
            throw new BusinessLogicException(PASSWORD_NOT_VALID);
        }
    }

    /**
     * Password 암호화   //리팩토링 (클래스 분리)
     */
    public static String encode(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes());
            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 프로필 사진
     */
    public String getImage(long memberId) {

        Member member = findById(memberId);
        MemberImage image = Optional.ofNullable(member.getMemberImage()).
                orElseThrow(() -> new BusinessLogicException(IMAGE_NOT_FOUND));

        return "file:" + memberImageService.getFullPath(image.getFileName());
    }
}