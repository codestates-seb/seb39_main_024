package com.codestates.flyaway.web.login.controller;

import com.codestates.flyaway.global.exception.BusinessLogicException;
import com.codestates.flyaway.web.login.dto.SessionDto;
import com.codestates.flyaway.web.login.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.codestates.flyaway.global.exception.ExceptionCode.*;
import static com.codestates.flyaway.web.login.dto.LoginDto.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @ApiOperation(value = "로그인 API")
    @PostMapping("/login")
    public LoginResponse login(HttpServletRequest request,
                               @RequestBody LoginRequest loginRequest) {

        SessionDto member = loginService.login(loginRequest);
        HttpSession session = request.getSession();

        if (session.getAttribute("member") == null) {

            session.setAttribute("member", member);
            log.info("로그인 성공 - {}", session.getId());

            return new LoginResponse("로그인 성공");
        }
//        return new LoginResponse("이미 로그인 된 사용자입니다.");
        throw new BusinessLogicException(MEMBER_ALREADY_AUTHORIZED);
    }

    @ApiOperation(value = "로그아웃 API")
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            log.info("로그아웃 성공 - {}", session.getId());

            return "로그아웃 성공";
        }
//        return "로그인 되지 않은 사용자입니다.";
        throw new BusinessLogicException(MEMBER_NOT_AUTHORIZED);
    }
}
