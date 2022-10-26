package com.codestates.flyaway.domain.member.entity;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.comment.entity.Comment;
import com.codestates.flyaway.domain.likes.Likes;
import com.codestates.flyaway.domain.memberimage.MemberImage;
import com.codestates.flyaway.domain.record.entity.Record;
import com.codestates.flyaway.domain.video.entity.Video;
import com.codestates.flyaway.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.*;

import static com.codestates.flyaway.domain.member.util.MemberUtil.*;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
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

    @OneToOne(fetch = LAZY, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private MemberImage memberImage;

    @OneToMany(mappedBy = "member", cascade = REMOVE)
    private List<Record> records = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = REMOVE)
    private List<Video> videos = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private Set<Likes> likes = new HashSet<>();

    private String name;
    private String email;
    private String password;

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Member(String name, String email, String password, List<Record> records) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.records = records;
    }

    public Member(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //==================


    public void setMemberImage(MemberImage memberImage) {
        this.memberImage = memberImage;
    }

    public void update(String name, String password) {
        Optional.ofNullable(name)
                .ifPresent(n -> this.name = n);
        Optional.ofNullable(password)
                .ifPresent(p -> this.password = encode(p));
//        Optional.ofNullable(password)
//                .ifPresent(p -> {
//                    checkPassword(p);
//                    this.password = encode(p);
//                });
    }
}
