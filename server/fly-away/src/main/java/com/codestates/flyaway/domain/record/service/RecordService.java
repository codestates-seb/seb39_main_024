package com.codestates.flyaway.domain.record.service;

import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.service.MemberService;
import com.codestates.flyaway.domain.record.entity.Record;
import com.codestates.flyaway.domain.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static com.codestates.flyaway.web.record.dto.RecordDto.*;
import static com.codestates.flyaway.web.record.dto.RecordDto.InsertResponseDto.*;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final MemberService memberService;

    //운동시간 기록
    public InsertResponseDto insertRecord(long memberId, InsertRequestDto insertDto) {

        String date = LocalDate.now().toString();

        Optional<Record> findRecord = recordRepository.findByMemberIdAndDate(memberId, date);
        Member findMember = memberService.findById(memberId);

        //해당 날짜에 이미 기록이 있는경우
        if (findRecord.isPresent()) {
            Record record = findRecord.get();
            //운동 시간 추가
            record.addRecord(insertDto.getRecord());
            //누적 시간 추가
            findMember.addTotalRecord(insertDto.getRecord());

            return recordToInsertResponse(memberId, record);
        }

        //해당 날짜 첫 운동기록인 경우
        //새로운 Record 생성
        Record newRecord = new Record(date, insertDto.getRecord());
        newRecord.setMember(findMember);

        //누적시간 추가
        findMember.addTotalRecord(insertDto.getRecord());

        Record savedRecord = recordRepository.save(newRecord);
        return recordToInsertResponse(memberId, savedRecord);
    }


}
