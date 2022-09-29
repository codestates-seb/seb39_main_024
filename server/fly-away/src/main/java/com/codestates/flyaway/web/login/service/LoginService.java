package com.codestates.flyaway.web.login.service;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import com.codestates.flyaway.web.login.dto.SessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.flyaway.domain.member.util.MemberUtil.*;
import static com.codestates.flyaway.global.exception.ExceptionCode.*;
import static com.codestates.flyaway.web.login.dto.LoginDto.*;
import static com.codestates.flyaway.web.login.dto.SessionDto.*;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public SessionDto login(LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        //이메일 확인
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(EMAIL_NOT_EXISTS));

        //비밀번호 확인
        if (encode(password).equals(member.getPassword())) {
            return toSession(member);
        }
        throw new BusinessLogicException(PASSWORD_NOT_MATCH);
    }
}
