package com.codestates.flyaway.domain.comment.service;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.board.service.BoardService;
import com.codestates.flyaway.domain.comment.entity.Comment;
import com.codestates.flyaway.domain.comment.repository.CommentRepository;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import com.codestates.flyaway.global.exception.ExceptionCode;
import com.codestates.flyaway.web.comment.dto.CommentDto;
import com.codestates.flyaway.web.comment.dto.CommentDto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.flyaway.web.comment.dto.CommentDto.CommentResponseDto.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardService boardService;

    public CommentResponseDto write(CommentDto.Write writeDto) {

        Board board = boardService.findById(writeDto.getBoardId());
        board.addCommentCount();

        Comment comment = new Comment(writeDto.getContent());
        comment.setBoard(board);
        commentRepository.save(comment);

        return toResponseDto(comment);
    }

    public CommentResponseDto update(CommentDto.Update updateDto) {

        final Comment comment = commentRepository.getReferenceById(updateDto.getCommentId());
        comment.update(updateDto.getCommentId(), updateDto.getContent());

        return toResponseDto(comment);
    }

    public Page<Comment> readByBoardId(Long boardId, Pageable pageable) {

        boardService.findById(boardId);

        return commentRepository.findByBoardId(boardId, pageable);
    }
    public void delete(Long commentId) {

        Comment comment = findById(commentId);
        commentRepository.delete(comment);
    }

    public Comment findById(Long commentId) {

        return commentRepository.findById(commentId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }
}
