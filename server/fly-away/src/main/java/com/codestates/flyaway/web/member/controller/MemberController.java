package com.codestates.flyaway.web.member.controller;

import com.codestates.flyaway.domain.member.service.MemberService;
import com.codestates.flyaway.global.dto.SingleResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.codestates.flyaway.web.auth.dto.AuthDto.*;
import static com.codestates.flyaway.web.member.dto.MemberDto.*;
import static org.springframework.http.HttpStatus.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "회원 가입", notes = "이메일 형식, 비밀번호 자릿수 검증")
    @PostMapping("/join")
    public ResponseEntity join(JoinRequestDto joinRequestDto) throws IOException {

        JoinResponseDto joinResponse = memberService.join(joinRequestDto);
        return new ResponseEntity(new SingleResponseDto<>(joinResponse), CREATED);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "이메일 형식, 비밀번호가 존재할 경우에만 검증")
    @PatchMapping("/{memberId}")
    public ResponseEntity update(@PathVariable long memberId,
                                 @RequestBody UpdateRequestDto updateRequestDto) {

        updateRequestDto.setMemberId(memberId);
        UpdateResponseDto updated = memberService.update(updateRequestDto);

        return new ResponseEntity(new SingleResponseDto<>(updated), OK);
    }

    @ApiOperation(value = "회원 프로필 정보")
    @GetMapping("/{memberId}")
    public ResponseEntity findMember(@PathVariable long memberId) {
        MemberProfileResponseDto findMember = memberService.findByIdFetch(memberId);

        return new ResponseEntity<>(new SingleResponseDto<>(findMember), OK);
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/{memberId}")
    public ResponseEntity delete(@PathVariable long memberId) {
        memberService.delete(memberId);
        return new ResponseEntity<>(NO_CONTENT);
    }

}
