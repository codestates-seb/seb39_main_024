package com.codestates.flyaway.domain.comment.service;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.board.service.BoardService;
import com.codestates.flyaway.domain.comment.entity.Comment;
import com.codestates.flyaway.domain.comment.repository.CommentRepository;
import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.service.MemberService;
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

import java.util.List;
import java.util.Objects;

import static com.codestates.flyaway.global.exception.ExceptionCode.NOT_AUTHORIZED;
import static com.codestates.flyaway.web.comment.dto.CommentDto.CommentResponseDto.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardService boardService;
    private final MemberService memberService;

    public CommentResponseDto write(CommentDto.Write writeDto) {

        Member member = memberService.findById(writeDto.getMemberId());
        Board board = boardService.findById(writeDto.getBoardId());
        board.addCommentCount();
        Comment comment = new Comment(writeDto.getContent());
        comment.setMember(member);
        comment.setBoard(board);
        commentRepository.save(comment);

        return toResponseDto(comment);
    }

    public CommentResponseDto update(CommentDto.Update updateDto) {

        final Comment comment = commentRepository.getReferenceById(updateDto.getCommentId());
        if(!Objects.equals(comment.getMember().getId(), updateDto.getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.NOT_AUTHORIZED);
        }
        comment.update(updateDto.getCommentId(), updateDto.getContent());

        return toResponseDto(comment);
    }

    public List<CommentDto.MultiCommentDto> readByBoardId(Long boardId, Pageable pageable) {

        boardService.findById(boardId);
        Page<Comment> comments = commentRepository.findByBoardId(boardId, pageable);
        List<Comment> commentList = comments.getContent();

        return CommentDto.MultiCommentDto.toResponsesDto(commentList);
    }
    public void delete(CommentDto.Delete deleteDto) {

        Comment comment = findById(deleteDto.getCommentId());
        if(!Objects.equals(comment.getMember().getId(), deleteDto.getMemberId())) {
            throw new BusinessLogicException(NOT_AUTHORIZED);
        }
        commentRepository.delete(comment);
    }

    public Comment findById(Long commentId) {

        return commentRepository.findById(commentId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }
}
