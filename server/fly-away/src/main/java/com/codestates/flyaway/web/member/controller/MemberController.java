package com.codestates.flyaway.web.member.controller;

import com.codestates.flyaway.domain.member.service.MemberService;
import com.codestates.flyaway.global.dto.SingleResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public SingleResponseDto join(@Validated @RequestBody JoinRequestDto joinRequestDto) {

        JoinResponseDto joinResponse = memberService.join(joinRequestDto);
        return new SingleResponseDto<>(joinResponse);
    }

    @ApiOperation(value = "회원 프로필 이미지 API")
    @GetMapping(value = "/{memberId}/image")
    public Resource getImage(@PathVariable long memberId) {
        return memberService.getImage(memberId);
    }

    @ApiOperation(value = "회원 정보 수정")
    @PatchMapping("/{memberId}")
    public SingleResponseDto update(@PathVariable long memberId,
                                    @Validated UpdateRequestDto updateRequestDto) {

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
    public String delete(@PathVariable long memberId, HttpServletRequest request) {
        memberService.delete(memberId);

        HttpSession session = request.getSession(false);
        session.invalidate();

        return "회원 탈퇴 성공";
    }
}
