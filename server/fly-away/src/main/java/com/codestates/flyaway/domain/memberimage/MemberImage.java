package com.codestates.flyaway.domain.memberimage;

import com.codestates.flyaway.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MemberImage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "memberImage")
    private Member member;

    private String fileOriName;
    private String fileUrl;
    private String fileName;

    public MemberImage(String fileOriName, String fileUrl, String fileName) {
        this.fileOriName = fileOriName;
        this.fileUrl = fileUrl;
        this.fileName = fileName;
    }


    //=====================


    public void setMember(Member member) {
        this.member = member;
        member.setMemberImage(this);
    }
}
