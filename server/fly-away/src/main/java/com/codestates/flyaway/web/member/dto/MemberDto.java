package com.codestates.flyaway.web.member.dto;

import com.codestates.flyaway.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.codestates.flyaway.web.record.dto.RecordDto.*;

@Getter
public class MemberDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberProfileResponseDto {
        private long id;
        private String email;
        private String name;
        private List<RecordProfileResponseDto> records;
        private long totalRecord;

        public static MemberProfileResponseDto memberToProfileResponse(Member member) {
            return new MemberProfileResponseDto(
                    member.getId(),
                    member.getEmail(),
                    member.getName(),
                    member.getRecords()
                            .stream()
                            .map(record -> new RecordProfileResponseDto(record.getDate(), record.getRecord()))
                            .collect(Collectors.toList()),
                    member.getTotalRecord());
        }
    }
}
