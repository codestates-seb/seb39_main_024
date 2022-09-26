package com.codestates.flyaway.domain.board.entity;

import com.codestates.flyaway.domain.category.entity.Category;
import com.codestates.flyaway.domain.comment.entity.Comment;
import com.codestates.flyaway.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getBoards().add(this);
    }

    public void addViewCount() {
        this.viewCount++;
    }

    public void addCommentCount() {
        this.commentCount++;
    }
}
