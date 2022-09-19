package com.codestates.flyaway.domain.member.entity;

import com.codestates.flyaway.domain.record.entity.Record;
import com.codestates.flyaway.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Record> records = new ArrayList<>();

    private String name;
    private String email;
    private String password;

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //==================


    public void update(String name, String email, String password) {
        Optional.ofNullable(name)
                .ifPresent(n -> this.name = n);
        Optional.ofNullable(email)
                .ifPresent(e -> this.email = e);
        Optional.ofNullable(password)
                .ifPresent(p -> this.password = p);
    }
}
