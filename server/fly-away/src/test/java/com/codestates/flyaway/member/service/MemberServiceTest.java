package com.codestates.flyaway.member.service;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import com.codestates.flyaway.domain.member.service.MemberService;
import com.codestates.flyaway.domain.record.entity.Record;
import com.codestates.flyaway.domain.record.repository.RecordRepository;
import com.codestates.flyaway.web.member.dto.MemberDto.JoinRequest;
import com.codestates.flyaway.web.member.dto.MemberDto.JoinResponse;
import com.codestates.flyaway.web.member.dto.MemberDto.UpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.codestates.flyaway.domain.member.util.MemberUtil.*;
import static com.codestates.flyaway.web.member.dto.MemberDto.*;
import static java.time.LocalDate.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RecordRepository recordRepository;

    @InjectMocks
    private MemberService memberService;

    @DisplayName("회원 가입")
    @Test
    void joinTest() {
        //given
        JoinRequest request = new JoinRequest("김코딩", "kimcode@gmail.com", "asdf123!");

        given(memberRepository.save(any(Member.class)))
                .willReturn(request.toEntity());

        //when
        JoinResponse response = memberService.join(request);

        //then
        assertThat(response.getEmail()).isEqualTo(request.getEmail());
    }

    @DisplayName("회원정보 수정")
    @Test
    void updateTest() {
        //given
        Member member = new Member("김코딩", "kimcode@gmail.com", "asdf123!");
        UpdateRequest request = new UpdateRequest(1L, "이코딩", "qwer456@", null);

        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));

        //when
        memberService.update(request);

        //then
        assertThat(member.getName()).isEqualTo(request.getName());
        assertThat(member.getPassword()).isEqualTo(encode(request.getPassword()));
    }

    @DisplayName("프로필 조회")
    @Test
    void profileTest() {
        //given
        Member member = new Member(1L, "김코딩", "kimcode@gmail.com", "asdf123!");
        List<Record> records = List.of(new Record(now(), 10), new Record(now(), 20));

        given(memberRepository.findByIdFetch(anyLong()))
                .willReturn(Optional.of(member));
        given(recordRepository.findByMemberId(anyLong()))
                .willReturn(records);

        //when
        MemberProfileResponse profile = memberService.findByIdFetch(1);

        //then
        assertThat(profile.getTotalRecord()).isEqualTo(30);
        assertThat(profile.getEmail()).isEqualTo(member.getEmail());
    }
}
