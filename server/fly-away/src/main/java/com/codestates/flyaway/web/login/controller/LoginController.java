package com.codestates.flyaway.web.login.controller;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.login.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.codestates.flyaway.domain.login.util.JwtUtil.AUTHORIZATION;
import static com.codestates.flyaway.domain.login.util.JwtUtil.PREFIX;
import static com.codestates.flyaway.web.login.dto.LoginDto.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @ApiOperation(value = "로그인 API")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        String accessToken = loginService.login(loginRequest);

        Member member = loginService.findByEmail(loginRequest.getEmail());

        response.addHeader("memberId", String.valueOf(member.getId()));
        response.addHeader(AUTHORIZATION, accessToken);

        return new LoginResponse("로그인 성공");
    }

    @ApiOperation(value = "로그아웃 API")
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");
        String accessToken = request.getHeader(AUTHORIZATION).replace(PREFIX, "");
        log.info("### 로그아웃 요청 - {}", email);

        loginService.logout(email, accessToken);

        return "로그아웃 성공";
    }
}
