package com.codestates.flyaway.domain.login.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.codestates.flyaway.domain.redis.RedisUtil;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

import static com.codestates.flyaway.global.exception.ExceptionCode.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {

    private final RedisUtil redisUtil;

    @Value("${spring.jwt.secret}")
    public String SECRET_KEY;

    public static final String PREFIX = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String EMAIL = "email";
    public static final String SUBJECT = "flyaway token";

    public static final long ACCESS_TOKEN_VALIDATION_SECOND = 1000L * 60 * 30;  //30분
    public static final long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 24 * 3;  //3일

    public String createAccessToken(String email) {
        return PREFIX + JWT.create()
                .withSubject(SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDATION_SECOND))
                .withClaim(EMAIL, email)
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public String createRefreshToken(String email) {
        return JWT.create()
                .withSubject(SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDATION_SECOND))  ///////////////////////
                .withClaim(EMAIL, email)
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public Map<String, String> verifyToken(String token) {
        //토큰 만료 확인
        if (isExpired(token)) {
            String email = getEmail(token);
            String refreshToken = redisUtil.getData(email);

            //refresh token 만료 확인 -> 강제 로그아웃
            if (refreshToken == null) {
                log.info("### refresh token expired - {}", email);

                throw new BusinessLogicException(REFRESH_TOKEN_EXPIRED);
            }

            //access token 재발급
            String accessToken = createAccessToken(email);
            //기존 refresh token 초기화
            redisUtil.setDataExpire(email, refreshToken, REFRESH_TOKEN_VALIDATION_SECOND);

            return Map.of(AUTHORIZATION, accessToken);
        }

        return Map.of(EMAIL,
                JWT.require(Algorithm.HMAC512(SECRET_KEY))
                        .build()
                        .verify(token)
                        .getClaim(EMAIL)
                        .asString());
    }

    private boolean isExpired(String token) {
        return JWT.decode(token).getExpiresAt().before(new Date());
    }

    public Long getExpiration(String token) {
        return JWT.decode(token).getExpiresAt().getTime() - System.currentTimeMillis();
    }

    private String getEmail(String token) {
        return JWT.decode(token).getClaim(EMAIL).asString();
    }
}
