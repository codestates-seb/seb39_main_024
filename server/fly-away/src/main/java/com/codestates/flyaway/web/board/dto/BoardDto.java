package com.codestates.flyaway.web.board.dto;

import com.codestates.flyaway.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardDto {

    @Getter
    @AllArgsConstructor
    public static class Create {

        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class Update {

        @Nullable
        private Long boardId;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }


    @Getter
    @AllArgsConstructor
    public static class BoardResponseDto {

        private Long boardId;
        private String title;
        private String content;
        private int viewCount;
        private LocalDateTime createdAt;

        public static BoardResponseDto boardToBoardResponse(Board board){

            return new BoardResponseDto(
                    board.getId(),
                    board.getTitle(),
                    board.getContent(),
                    board.getViewCount(),
                    board.getCreatedAt());
        }
    }

    @Getter
    @AllArgsConstructor
    public static class MultiBoardDto {

        private Long boardId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private int viewCount;

        public static List<MultiBoardDto> boardsToResponses(List<Board> boards) {

            return boards.stream()
                    .map(board -> new MultiBoardDto(
                            board.getId(),
                            board.getTitle(),
                            board.getContent(),
                            board.getCreatedAt(),
                            board.getViewCount()))
                    .collect(Collectors.toList());
        }
    }
}