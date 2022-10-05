package com.codestates.flyaway.web.login.controller;

import com.codestates.flyaway.web.login.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.codestates.flyaway.web.login.dto.LoginDto.*;
import static com.codestates.flyaway.web.login.util.JwtProperties.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @ApiOperation(value = "로그인 API")
    @PostMapping("/login")
    public LoginResponse login(HttpServletResponse response, @RequestBody LoginRequest loginRequest) {

        String token = loginService.login(loginRequest);

        response.addHeader(HEADER, PREFIX + token);
        return new LoginResponse("로그인 성공");
    }

    @ApiOperation(value = "로그아웃 API")
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");
        log.info("email ={}", email);
        loginService.logout(email);

        return "로그아웃 성공";

//        return "로그인 되지 않은 사용자입니다.";
//        throw new BusinessLogicException(MEMBER_NOT_AUTHORIZED);
    }
}
