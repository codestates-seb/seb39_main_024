package com.codestates.flyaway.domain.record.entity;

import com.codestates.flyaway.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Record {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate date;
    private Long record;


    public Record(LocalDate date, long record) {
        this.date = date;
        this.record = record;
    }


    //============================

    //연관관계
    public void setMember(Member member) {
        this.member = member;
        member.getRecords().add(this);
    }

    //운동시간 추가
    public void addRecord(long record) {
        this.record += record;
    }
}
