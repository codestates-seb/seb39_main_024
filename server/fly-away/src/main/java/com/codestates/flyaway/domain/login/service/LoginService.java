package com.codestates.flyaway.domain.login.service;

import com.auth0.jwt.JWT;
import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.*;
import static com.codestates.flyaway.domain.member.util.MemberUtil.*;
import static com.codestates.flyaway.global.exception.ExceptionCode.*;
import static com.codestates.flyaway.web.login.dto.LoginDto.*;
import static com.codestates.flyaway.domain.login.util.JwtProperties.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public String login(LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        //이메일 확인
        Member member = findByEmail(email);

        //비밀번호 확인
        if (encode(password).equals(member.getPassword())) {
            log.info("로그인 성공 - {}", member.getEmail());
            member.setLogin();
            return createJwt(member);
        }
        throw new BusinessLogicException(PASSWORD_NOT_MATCH);
    }


    private String createJwt(Member member) {

        return JWT.create()
                .withSubject("flyaway token")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 20)))
                .withClaim("email", member.getEmail())
                .sign(HMAC512(SECRET));
    }

    public void logout(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(EMAIL_NOT_EXISTS));

        member.setLogout();
        log.info("로그아웃 성공 - {}", member.getEmail());
    }

    public Member findByEmail(String email) {

        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(EMAIL_NOT_EXISTS));
    }
}
