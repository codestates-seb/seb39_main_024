package com.codestates.flyaway.record.service;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.service.MemberService;
import com.codestates.flyaway.domain.record.entity.Record;
import com.codestates.flyaway.domain.record.repository.RecordRepository;
import com.codestates.flyaway.domain.record.service.RecordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.codestates.flyaway.web.record.dto.RecordDto.*;
import static java.time.LocalDate.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RecordServiceTest {

    @Mock
    private RecordRepository recordRepository;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private RecordService recordService;

//    @BeforeEach
//    void before() {
//        Member member1 = new Member("kim", "member1@gmail.com", "pw");
//        Member member2 = new Member("park","member2@gmail.com", "pw");
//        Member member3 = new Member("lee", "member3@gmail.com", "pw");
//        Member member4 = new Member("code","member4@gmail.com", "pw");
//        memberRepository.save(member1);
//        memberRepository.save(member2);
//        memberRepository.save(member3);
//        memberRepository.save(member4);
//    }

    @DisplayName("운동 시간 기록 - 해당 날짜 첫 기록")
    @Test
    void newRecordTest() {
        //given
        Member member = new Member(1L, "kim", "member1@gmail.com", "pw");
        InsertRequest request = new InsertRequest(1800);

        given(recordRepository.findByMemberIdAndDate(anyLong(), eq(now())))
                .willReturn(Optional.empty());

        given(memberService.findById(anyLong()))
                .willReturn(member);

        //when
        InsertResponse response = recordService.insertRecord(1L, request);
        long totalRecord = member.getRecords().stream().mapToLong(Record::getRecord).sum();

        //then
        assertThat(response.getRecord()).isEqualTo(request.getRecord());
        assertThat(totalRecord).isEqualTo(request.getRecord());
    }

    @DisplayName("운동 시간 기록 - 누적 기록 추가")
    @Test
    void addRecordTest() {
        //given
        Member member = new Member(1L, "kim", "member1@gmail.com", "pw");
        Record record = new Record(now(), 500);

        InsertRequest request = new InsertRequest(1800);

        given(recordRepository.findByMemberIdAndDate(anyLong(), eq(now())))
                .willReturn(Optional.of(record));

        given(memberService.findById(anyLong()))
                .willReturn(member);

        //when
        recordService.insertRecord(1L, request);
        long totalRecord = member.getRecords()
                .stream()
                .mapToLong(Record::getRecord)
                .sum();

        //then
        assertThat(totalRecord).isEqualTo(2300);
    }
}
