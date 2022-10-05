package com.codestates.flyaway.web.board.dto;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.boardimage.entity.BoardImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private Long memberId;
        private Long categoryId;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {

        private Long memberId;
        private Long categoryId;
        @Nullable
        private Long boardId;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Delete {

        private Long memberId;
        private Long boardId;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardResponseDto {

        private Long memberId;
        private Long categoryId;
        private Long boardId;
        private String title;
        private String content;
        private List<Long> imageId;
        private int viewCount;
        private int likecount;
        private LocalDateTime createdAt;

        public static BoardResponseDto toResponseDto(Board board){

            return new BoardResponseDto(
                    board.getMember().getId(),
                    board.getCategory().getId(),
                    board.getId(),
                    board.getTitle(),
                    board.getContent(),
                    board.getImages().stream().map(BoardImage::getId).collect(Collectors.toList()),
                    board.getViewCount(),
                    board.getLikeCount(),
                    board.getCreatedAt());
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MultiBoardDto {

        private Long boardId;
        private String title;
        private String content;
        private List<Long> imageId;
        private LocalDateTime createdAt;
        private int viewCount;

        public static List<MultiBoardDto> toResponsesDto(List<Board> boards) {

            return boards.stream()
                    .map(board -> new MultiBoardDto(
                            board.getId(),
                            board.getTitle(),
                            board.getContent(),
                            board.getImages().stream().map(BoardImage::getId).collect(Collectors.toList()),
                            board.getCreatedAt(),
                            board.getViewCount()))
                    .collect(Collectors.toList());
        }
    }
}