package com.codestates.flyaway.web.login.interceptor;

import com.codestates.flyaway.domain.login.util.JwtUtil;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import com.codestates.flyaway.domain.redis.RedisUtil;
import com.codestates.flyaway.global.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.codestates.flyaway.domain.login.util.JwtUtil.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final MemberRepository memberRepository;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        //header 확인
        String header = request.getHeader(AUTHORIZATION);
        if (header == null || !header.startsWith(PREFIX)) {
            errorResponse(response, "token is missing");
            return false;
        }

        String token = request.getHeader(AUTHORIZATION).replace(PREFIX, "");

        //blacklist 확인
        if (redisUtil.isBlacklist(token)) {
            log.info("### access token from blacklist - {}", token);
            errorResponse(response, "token from blacklist");
            return false;
        }

        //토큰 검증
        Map<String, String> verified = jwtUtil.verifyToken(token);

        if (verified.containsKey(AUTHORIZATION)) {
            response.addHeader(AUTHORIZATION, verified.get(AUTHORIZATION));
            errorResponse(response, "reissued access token");
            return false;
        }

        //이메일 검증
        String email = verified.get("email");
        if (email == null || !memberRepository.existsByEmail(email)) {
            errorResponse(response, "email from token not valid");
            return false;
        }

        if (request.getRequestURI().equals("/logout")) {
            log.info("### logout interceptor - {}", email);
            request.setAttribute("email", email);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            log.info("exception occurred={}", ex.getMessage());
        }
    }

    private void errorResponse(HttpServletResponse response, String message) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String error = mapper.writeValueAsString(ErrorResponse.of(HttpStatus.valueOf(401), message));

        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(error);
    }
}
