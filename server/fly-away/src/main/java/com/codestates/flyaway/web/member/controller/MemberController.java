package com.codestates.flyaway.web.member.controller;

import com.codestates.flyaway.domain.member.service.MemberService;
import com.codestates.flyaway.global.dto.SingleResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.MalformedURLException;

import static com.codestates.flyaway.web.member.dto.MemberDto.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "회원 가입", notes = "이메일 중복 확인")
    @ResponseStatus(value = CREATED)
    @PostMapping("/join")
    public SingleResponseDto join(JoinRequestDto joinRequestDto) { // todo : Auth api 로 분리 고려

        JoinResponseDto joinResponse = memberService.join(joinRequestDto);
        return new SingleResponseDto<>(joinResponse);
    }

    @ApiOperation(value = "회원 프로필 이미지 API")
    @GetMapping(value = "/{memberId}/image")
    public Resource getImage(@PathVariable long memberId) {
        return memberService.getImage(memberId);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "이메일이 존재할 경우 중복 확인")
    @PatchMapping("/{memberId}")
    public SingleResponseDto update(@PathVariable long memberId,
                                 UpdateRequestDto updateRequestDto) {

        updateRequestDto.setMemberId(memberId);
        UpdateResponseDto updated = memberService.update(updateRequestDto);

        return new SingleResponseDto<>(updated);
    }

    @ApiOperation(value = "회원 프로필 정보")
    @GetMapping("/{memberId}")
    public SingleResponseDto findMember(@PathVariable long memberId) {
        MemberProfileResponseDto findMember = memberService.findByIdFetch(memberId);

        return new SingleResponseDto<>(findMember);
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/{memberId}")
    public HttpStatus delete(@PathVariable long memberId) {
        memberService.delete(memberId);
        return NO_CONTENT;
    }
}
