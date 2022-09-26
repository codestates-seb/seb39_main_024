package com.codestates.flyaway.record.service;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import com.codestates.flyaway.domain.record.entity.Record;
import com.codestates.flyaway.domain.record.repository.RecordRepository;
import com.codestates.flyaway.domain.record.service.RecordService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static com.codestates.flyaway.web.record.dto.RecordDto.*;
import static java.time.LocalDate.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class InsertRecordTest {

    @Autowired
    private RecordService recordService;
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void before() {
        Member member1 = new Member(1L, null, new ArrayList<>(), "kim", "member1@gmail.com", "pw");
        Member member2 = new Member(2L, null, new ArrayList<>(), "park","member2@gmail.com", "pw");
        Member member3 = new Member(3L, null, new ArrayList<>(), "lee", "member3@gmail.com", "pw");
        Member member4 = new Member(4L, null, new ArrayList<>(), "code","member4@gmail.com", "pw");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
    }
    @AfterEach
    void rollback() {
        memberRepository.deleteAll();
        recordRepository.deleteAll();
    }


    @DisplayName("운동시간 기록 테스트 (insertRecord)")
    @Test
    void insertRecordTest() {
        InsertRequestDto recordDto = new InsertRequestDto(1800);
        recordService.insertRecord(1L, recordDto);

        InsertRequestDto recordDto2 = new InsertRequestDto(300);
        recordService.insertRecord(1L, recordDto2);

        Record record = recordRepository.findByMemberIdAndDate(1L, now()).get();

        assertThat(record.getRecord()).isEqualTo(recordDto.getRecord() + recordDto2.getRecord());
        assertThat(record.getDate()).isEqualTo(now());
    }
}
