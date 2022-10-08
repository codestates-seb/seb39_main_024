package com.codestates.flyaway.domain.video.entity;

import com.codestates.flyaway.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Video {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long videoId;
    private String title;
    private String url;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Video(long videoId, String title, String url, Member member) {
        this.videoId = videoId;
        this.title = title;
        this.url = url;
        this.member = member;
    }
}
