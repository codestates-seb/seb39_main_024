package com.codestates.flyaway.record.repository;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.repository.MemberRepository;
import com.codestates.flyaway.domain.record.entity.Record;
import com.codestates.flyaway.domain.record.repository.RecordRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
class RecordRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RecordRepository recordRepository;

    @BeforeEach
    void before() {
        Member member1 = new Member(1L, new ArrayList<>(), "kim", "member1@gmail.com", "pw", 0L);

        Record record1 = new Record(now().toString(), 10);
        record1.setMember(member1);

        memberRepository.save(member1);
        recordRepository.save(record1);
    }


    @DisplayName("회원 ID와 날짜를 기반으로 기록 조회 (findByMemberIdAndDate)")
    @Test
    void findRecordTest() {

        Record record = recordRepository.findByMemberIdAndDate(1L, LocalDate.now().toString()).get();

        assertThat(record.getMember().getId()).isEqualTo(1);
        assertThat(record.getRecord()).isEqualTo(10);
    }
}
