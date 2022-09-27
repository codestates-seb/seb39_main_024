package com.codestates.flyaway.domain.boardimage.entity;

import com.codestates.flyaway.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileOriName;
    private String fileUrl;
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public BoardImage(String fileOriName, String fileUrl, String fileName) {
        this.fileOriName = fileOriName;
        this.fileUrl = fileUrl;
        this.fileName = fileName;
    }

    public void setBoard(Board board) {
        this.board = board;
        if(!board.getImages().contains(this)) {
            board.getImages().add(this);
        }
    }
}
