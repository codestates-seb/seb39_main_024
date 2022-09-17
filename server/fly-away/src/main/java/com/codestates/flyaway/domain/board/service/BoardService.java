package com.codestates.flyaway.domain.board.service;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.board.repository.BoardRepository;
import com.codestates.flyaway.global.dto.MultiResponseDto;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.flyaway.global.exception.ExceptionCode.*;
import static com.codestates.flyaway.web.board.dto.BoardDto.*;
import static com.codestates.flyaway.web.board.dto.BoardDto.BoardResponseDto.*;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto create(BoardDto.Create createDto) {

        Board board = new Board(createDto.getTitle(), createDto.getContent());
        boardRepository.save(board);

        return boardToBoardResponse(board);
    }

    @Transactional
    public BoardResponseDto update(Update updateDto) {
        long boardId = updateDto.getBoardId();
        Board board = findById(boardId);
        board.update(updateDto.getTitle(), updateDto.getContent());
        boardRepository.save(board);

        return boardToBoardResponse(board);
    }

    @Transactional(readOnly = true)
    public BoardResponseDto read(long boardId) {

        Board board = findById(boardId);
        board.addViewCount();

        return boardToBoardResponse(board);
    }

    public Page<Board> readAll(int page, int size) {

        return boardRepository.findAll(
                PageRequest.of(page - 1, size, Sort.by("id").descending()));
    }

    @Transactional
    public void delete(long boardId) {

        Board board = findById(boardId);
        boardRepository.delete(board);
    }

    public Board findById(long boardId) {

        return boardRepository.findById(boardId).orElseThrow(() ->
                new BusinessLogicException(ARTICLE_NOT_FOUND));
    }
}