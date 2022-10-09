package com.codestates.flyaway.web.member.controller;

import com.codestates.flyaway.domain.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public JoinResponse join(@Validated @RequestBody JoinRequest joinRequest) {
        return memberService.join(joinRequest);
    }

    @ApiOperation(value = "회원 정보 수정")
    @PatchMapping("/{memberId}")
    public UpdateResponse update(@PathVariable long memberId,
                                 @Validated UpdateRequest updateRequest) {

        updateRequest.setMemberId(memberId);
        return memberService.update(updateRequest);
    }

    @ApiOperation(value = "회원 프로필 이미지 API")
    @GetMapping(value = "/{memberId}/image")
    public String getImage(@PathVariable long memberId) {
        return memberService.getImageUrl(memberId);
    }

    @ApiOperation(value = "회원 프로필 정보")
    @GetMapping("/{memberId}")
    public MemberProfileResponse findMember(@PathVariable long memberId) {
        return memberService.findByIdFetch(memberId);
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/{memberId}")
    public String delete(@PathVariable long memberId) {
        memberService.delete(memberId);
        return "회원 탈퇴 성공";
    }
}
