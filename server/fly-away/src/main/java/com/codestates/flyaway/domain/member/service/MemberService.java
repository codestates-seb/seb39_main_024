package com.codestates.flyaway.domain.member.service;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import com.codestates.flyaway.domain.record.entity.Record;
import com.codestates.flyaway.domain.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

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
    private final RecordRepository recordRepository;

    private final String REG_EMAIL = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
    private final String REG_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,}$";  // 최소 8글자, 글자, 숫자, 특수문자 1개


    /**
     * 회원가입
     * @return 가입 완료된 회원의 id, name, email, createdAt
     */
    public JoinResponseDto join(JoinRequestDto joinRequestDto) throws IOException {

        verifyEmail(joinRequestDto.getEmail());
        verifyPassword(joinRequestDto.getPassword());
        joinRequestDto.setPassword(encode(joinRequestDto.getPassword()));

        Member member = joinRequestDto.toEntity();

        Member savedMember = memberRepository.save(member);
        return toJoinResponse(savedMember);
    }

    /**
     * 회원 정보 수정
     * @return 수정 완료된 회원의 id, name, email
     */
     public UpdateResponseDto update(UpdateRequestDto updateRequestDto) {

         String name = updateRequestDto.getName();
         String email = updateRequestDto.getEmail();
         String password = updateRequestDto.getPassword();

         if (email != null) {
             verifyEmail(email);
         }
         if (password != null) {
             verifyPassword(password);
         }

         Member member = findById(updateRequestDto.getMemberId());
         member.update(name, email, password);

         return toUpdateResponse(member);
     }

    /**
     * 회원 프로필 (특정 회원을 모든 운동기록과 함께 조회)
     * @return 회원 프로필 정보
     */
    @Transactional(readOnly = true)
    public MemberProfileResponseDto findByIdFetch(long memberId) {

        Member findMember = memberRepository.findByIdFetch(memberId)
                .orElseThrow(() -> new RuntimeException(new Exception().getCause()));   //Custom Exception 으로 변경

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
        memberRepository.deleteById(memberId);
    }

    /**
     * 단순 조회 재사용 메서드
     */
    @Transactional(readOnly = true)
    public Member findById(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원 존재하지 않음"));   // Custom Exception 으로 변경
    }

    /**
     * Email 검증
     */
    @Transactional(readOnly = true)
    private void verifyEmail(String email) {

        if (!Pattern.matches(REG_EMAIL, email)) {
            throw new RuntimeException("이메일 형식이 올바르지 않습니다.");
        }

        memberRepository.findByEmail(email)
                .ifPresent(m -> {throw new RuntimeException("존재하는 이메일");});
    }

    /**
     * Password 검증
     */
    private void verifyPassword(String password) {
        if (!Pattern.matches(REG_PASSWORD, password)) {
            throw new RuntimeException("비밀번호 형식이 올바르지 않습니다.");
        }
    }

    /**
     * Password 암호화   ///리팩토링 (클래스 분리)
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


}