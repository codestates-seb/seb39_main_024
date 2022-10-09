package com.codestates.flyaway.domain.board.entity;

import com.codestates.flyaway.domain.boardimage.entity.BoardImage;
import com.codestates.flyaway.domain.category.entity.Category;
import com.codestates.flyaway.domain.comment.entity.Comment;
import com.codestates.flyaway.domain.likes.Likes;
import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board extends Auditable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String content;
    private int viewCount;
    private int likeCount;
    private int commentCount;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BoardImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private Set<Likes> likes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getBoards().add(this);
    }

    public void addImage(BoardImage boardImage) {
        this.images.add(boardImage);
        if(boardImage.getBoard() != this) {
            boardImage.setBoard(this);
        }
    }

    public void setLikes(Likes likes) {
        this.likes.add(likes);
    }

    public void addViewCount() {
        this.viewCount++;
    }

    public void addCommentCount() {
        this.commentCount++;
    }

    public void addLikeCount() {
        this.likeCount++;
    }

    public void dislike() {
        this.likeCount--;
    }
}
