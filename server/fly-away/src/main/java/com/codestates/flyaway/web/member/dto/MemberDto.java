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
    public static class JoinRequest {
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
    public static class JoinResponse {
        private Long memberId;
        private String name;
        private String email;
        private LocalDateTime createdAt;

        public static JoinResponse toJoinResponse(Member member) {
            return new JoinResponse(member.getId(), member.getName(), member.getEmail(), member.getCreatedAt());
        }
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class MemberProfileResponse {
        private long id;
        private String name;
        private String email;
        private List<RecordProfileResponse> records;
        private long totalRecord;

        public static MemberProfileResponse toProfileResponse(Member member, long totalRecord) {
            return new MemberProfileResponse(
                    member.getId(),
                    member.getName(),
                    member.getEmail(),
                    member.getRecords()
                            .stream()
                            .map(record -> new RecordProfileResponse(record.getDate(), record.getRecord()))
                            .collect(Collectors.toList()),
                    totalRecord);
        }
    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class UpdateRequest {
        private Long memberId;
        private String name;
        private String password;
        private MultipartFile image;
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class UpdateResponse {  // todo : 재사용 고려
        private Long memberId;
        private String name;
        private String email;
        private LocalDateTime modifiedAt;

        public static UpdateResponse toUpdateResponse(Member member) {
            return new UpdateResponse(member.getId(), member.getName(), member.getEmail(), member.getModifiedAt());
        }
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class MemberListDto {
        private Long memberId;
        private String name;
        private String email;
        private LocalDateTime modifiedAt;
    }
}
