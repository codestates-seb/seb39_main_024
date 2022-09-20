package com.codestates.flyaway.web.member.dto;

import com.codestates.flyaway.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.codestates.flyaway.web.record.dto.RecordDto.*;

@Getter
public class MemberDto {

    @Getter @NoArgsConstructor @AllArgsConstructor
    public static class MemberProfileResponseDto {
        private long id;
        private String name;
        private String email;
        private List<RecordProfileResponseDto> records;
        private long totalRecord;

        public static MemberProfileResponseDto memberToProfileResponse(Member member, long totalRecord) {
            return new MemberProfileResponseDto(
                    member.getId(),
                    member.getName(),
                    member.getEmail(),
                    member.getRecords()
                            .stream()
                            .map(record -> new RecordProfileResponseDto(record.getDate(), record.getRecord()))
                            .collect(Collectors.toList()),
                    totalRecord);
        }
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class UpdateRequestDto {

        private Long memberId;
        private String name;
        private String email;
        private String password;

        public Member toMember() {
            return new Member(this.getName(), this.getEmail(), this.getPassword());
        }
    }
    @Getter @NoArgsConstructor @AllArgsConstructor
    public static class UpdateResponseDto {
        private Long memberId;
        private String name;
        private String email;
        private LocalDateTime modifiedAt;

        public static UpdateResponseDto toUpdateResponse(Member member) {
            return new UpdateResponseDto(member.getId(), member.getName(), member.getEmail(), member.getModifiedAt());
        }
    }

    @Getter @NoArgsConstructor @AllArgsConstructor
    public static class MemberListDto {
        private Long memberId;
        private String name;
        private String email;
        private LocalDateTime modifiedAt;

        public static UpdateResponseDto toUpdateResponse(Member member) {
            return new UpdateResponseDto(member.getId(), member.getName(), member.getEmail(), member.getModifiedAt());
        }
    }
}
