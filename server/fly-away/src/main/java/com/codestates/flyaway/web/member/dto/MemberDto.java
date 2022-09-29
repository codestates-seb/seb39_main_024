package com.codestates.flyaway.web.member.dto;

import com.codestates.flyaway.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.codestates.flyaway.web.record.dto.RecordDto.*;

@Getter
public class MemberDto {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class JoinRequestDto {
        @NotEmpty
        private String name;
        @NotEmpty @Email
        private String email;
        @NotEmpty
        private String password;

        public Member toEntity() {
            return new Member(this.name, this.email, this.password);
        }
    }
    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class JoinResponseDto {
        private Long memberId;
        private String name;
        private String email;
        private LocalDateTime createdAt;

        public static JoinResponseDto toJoinResponse(Member member) {
            return new JoinResponseDto(member.getId(), member.getName(), member.getEmail(), member.getCreatedAt());
        }
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class MemberProfileResponseDto {
        private long id;
        private String name;
        private String email;
        private List<RecordProfileResponseDto> records;
        private long totalRecord;

        public static MemberProfileResponseDto toProfileResponse(Member member, long totalRecord) {
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

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class UpdateRequestDto {
        private Long memberId;
        private String name;
        private String password;
        private MultipartFile image;
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class UpdateResponseDto {  // todo : 재사용 고려
        private Long memberId;
        private String name;
        private String email;
        private LocalDateTime modifiedAt;

        public static UpdateResponseDto toUpdateResponse(Member member) {
            return new UpdateResponseDto(member.getId(), member.getName(), member.getEmail(), member.getModifiedAt());
        }
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
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
