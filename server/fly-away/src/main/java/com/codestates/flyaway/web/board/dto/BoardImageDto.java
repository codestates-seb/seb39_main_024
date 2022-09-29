package com.codestates.flyaway.web.board.dto;

import com.codestates.flyaway.domain.boardimage.entity.BoardImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardImageDto {

    private String fileOriName;
    private String fileUrl;
    private String fileName;

    public static BoardImageDto toResponseDto(BoardImage boardImage) {

        return new BoardImageDto(
                boardImage.getFileOriName(),
                boardImage.getFileUrl(),
                boardImage.getFileName());
    }
}
