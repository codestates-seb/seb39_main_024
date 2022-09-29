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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.codestates.flyaway.global.exception.ExceptionCode.*;
import static com.codestates.flyaway.web.login.dto.LoginDto.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;
    private static final String MEMBER = "member";

    @ApiOperation(value = "로그인 API")
    @PostMapping("/login")
    public LoginResponse login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginRequest loginRequest) {

        SessionDto member = loginService.login(loginRequest);
        Long id = member.getId();

        HttpSession session = request.getSession();
        if (session.getAttribute(MEMBER) == null) {
            session.setAttribute(MEMBER, id);    // todo : 세션 저장 객체 직렬화
            response.addHeader("memberId", String.valueOf(member.getId()));
            log.info("로그인 성공 - {}", session.getId());

            return new LoginResponse("로그인 성공");
        }
        throw new BusinessLogicException(MEMBER_ALREADY_AUTHORIZED);
    }

    @ApiOperation(value = "로그아웃 API")
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session.getAttribute(MEMBER) != null) {
            session.invalidate();
            log.info("로그아웃 성공 - {}", session.getId());

            return "로그아웃 성공";
        }
//        return "로그인 되지 않은 사용자입니다.";
        throw new BusinessLogicException(MEMBER_NOT_AUTHORIZED);
    }
}
