package com.codestates.flyaway.web.auth.dto;

import com.codestates.flyaway.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
public class AuthDto {

    @Getter
    @AllArgsConstructor
    public static class JoinRequestDto {
        @NotEmpty
        private String name;
        @NotEmpty @Email
        private String email;
        @NotEmpty
        private String password;
        private MultipartFile image;  ////

        public Member toEntity() {   ////비밀번호 암호화
            return new Member(this.getName(), this.getEmail(), this.getPassword());
        }
    }

    @Getter
    @AllArgsConstructor
    public static class JoinResponseDto {
        private Long memberId;
        private String name;
        private String email;
        private LocalDateTime createdAt;

        public static JoinResponseDto toJoinResponse(Member member) {
            return new JoinResponseDto(member.getId(), member.getName(), member.getEmail(), member.getCreatedAt());
        }
    }



}
