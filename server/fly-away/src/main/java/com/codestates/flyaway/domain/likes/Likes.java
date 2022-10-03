package com.codestates.flyaway.domain.likes;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder
    public Likes(Board board, Member member) {
        this.board = board;
        this.member = member;
    }
}
