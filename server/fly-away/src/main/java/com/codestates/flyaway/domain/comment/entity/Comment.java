package com.codestates.flyaway.domain.comment.entity;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.global.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Comment(String content) {
        this.content = content;
    }

    public void setBoard(Board board) {
        this.board = board;
        board.getComments().add(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }

    public void update(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
