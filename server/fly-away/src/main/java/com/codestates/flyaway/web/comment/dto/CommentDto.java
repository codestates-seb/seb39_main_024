package com.codestates.flyaway.web.comment.dto;

import com.codestates.flyaway.domain.comment.entity.Comment;
import com.codestates.flyaway.web.board.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Write {

        private Long boardId;
        @NotBlank
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Update {

        @Nullable
        private Long commentId;
        @NotBlank
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class CommentResponseDto {

        private Long commentId;
        private String content;
        private LocalDateTime createdAt;

        public static CommentDto.CommentResponseDto commentToCommentResponse(Comment comment) {

            return new CommentDto.CommentResponseDto(
                    comment.getId(),
                    comment.getContent(),
                    comment.getCreatedAt());
        }
    }

    @Getter
    @AllArgsConstructor
    public static class MultiCommentDto {

        private Long commentid;
        private String content;
        private LocalDateTime createdAt;

        public static List<MultiCommentDto> commentToResponses(List<Comment> comments) {

            return comments.stream()
                    .map(comment -> new MultiCommentDto(
                            comment.getId(),
                            comment.getContent(),
                            comment.getCreatedAt()))
                    .collect(Collectors.toList());
        }
    }
}