package com.codestates.flyaway.domain.member.service;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.flyaway.web.member.dto.MemberDto.*;
import static com.codestates.flyaway.web.member.dto.MemberDto.MemberProfileResponseDto.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원 존재하지 않음"));   // Custom Exception 으로 대체
    }

    //특정 회원을 모든 운동기록과 함께 조회 (회원 프로필)
    public MemberProfileResponseDto findByIdFetch(long memberId) {
        Member findMember = memberRepository.findByIdFetch(memberId)
                .orElseThrow(() -> new RuntimeException("회원 존재 x"));

        return memberToProfileResponse(findMember);
    }
}