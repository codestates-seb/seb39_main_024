package com.codestates.flyaway.web.comment;

import com.codestates.flyaway.domain.comment.entity.Comment;
import com.codestates.flyaway.domain.comment.service.CommentService;
import com.codestates.flyaway.global.dto.MultiResponseDto;
import com.codestates.flyaway.global.dto.SingleResponseDto;
import com.codestates.flyaway.web.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardId}/comment")
    public ResponseEntity writeComment(@PathVariable("boardId") Long boardId,
                                       @RequestBody CommentDto.Write writeDto) {

        writeDto.setBoardId(boardId);
        CommentDto.CommentResponseDto wrote = commentService.write(writeDto);

        return new ResponseEntity(new SingleResponseDto<>(wrote), HttpStatus.CREATED);
    }

    @PatchMapping("/{boardId}/comment/{commentId}")
    public ResponseEntity updateComment(@PathVariable("boardId") Long boardId,
                                        @PathVariable("commentId") Long commentId,
                                        @RequestBody CommentDto.Update updateDto) {

        updateDto.setCommentId(commentId);
        CommentDto.CommentResponseDto updated = commentService.update(updateDto);

        return new ResponseEntity(new SingleResponseDto<>(updated), HttpStatus.OK);
    }

    @GetMapping("/{boardId}/comment")
    public ResponseEntity readAllComment(@PathVariable("boardId") Long boardId,
                                         @RequestParam int page,
                                         @RequestParam int size) {

        Page<Comment> commentPage = commentService.realAll(page, size);
        List<Comment> comments = commentPage.getContent();
        List<CommentDto.MultiCommentDto> responses = CommentDto.MultiCommentDto.commentToResponses(comments);

        return new ResponseEntity(new MultiResponseDto<>(responses, commentPage), HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId) {

        commentService.delete(commentId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
