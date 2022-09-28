package com.codestates.flyaway.web.login.dto;

import com.codestates.flyaway.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class SessionDto {

    private Long id;

    public static SessionDto toSession(Member member) {
        return new SessionDto(member.getId());
    }
}
