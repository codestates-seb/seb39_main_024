package com.codestates.flyaway.domain.login.service;

import com.codestates.flyaway.domain.login.util.JwtUtil;
import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import com.codestates.flyaway.domain.redis.RedisUtil;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.flyaway.domain.login.util.JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND;
import static com.codestates.flyaway.domain.member.util.MemberUtil.*;
import static com.codestates.flyaway.global.exception.ExceptionCode.*;
import static com.codestates.flyaway.web.login.dto.LoginDto.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;

    public String login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        //이메일 확인
        Member member = findByEmail(email);

        //비밀번호 확인
        if (checkPassword(password, member.getPassword())) {
            log.info("로그인 성공 - {}", member.getEmail());

            String accessToken = jwtUtil.createAccessToken(email);
            String refreshToken = jwtUtil.createRefreshToken(email);
            redisUtil.setDataExpire(email, refreshToken, REFRESH_TOKEN_VALIDATION_SECOND);

            return accessToken;
        }

        throw new BusinessLogicException(PASSWORD_NOT_MATCH);
    }

    public void logout(String email, String accessToken) {
        //refresh token 삭제
        redisUtil.deleteData(email);

        //access token blacklist 등록
        Long expiration = jwtUtil.getExpiration(accessToken);
        redisUtil.setBlackList(accessToken, expiration);

        log.info("로그아웃 성공 - {}", email);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(EMAIL_NOT_EXISTS));
    }

    private boolean checkPassword(String input, String exact) {
        return encode(input).equals(exact);
    }
}
