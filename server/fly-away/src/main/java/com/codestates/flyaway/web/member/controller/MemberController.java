package com.codestates.flyaway.web.member.controller;

import com.codestates.flyaway.domain.member.service.MemberService;
import com.codestates.flyaway.global.dto.SingleResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.codestates.flyaway.web.member.dto.MemberDto.*;
import static org.springframework.http.HttpStatus.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;


    //회원 프로필 API
    @ApiOperation(value = "회원 프로필 정보")
    @GetMapping("/{memberId}")
    public ResponseEntity findMember(@PathVariable long memberId) {
        MemberProfileResponseDto findMember = memberService.findByIdFetch(memberId);

        return new ResponseEntity<>(new SingleResponseDto<>(findMember), OK);
    }


}
