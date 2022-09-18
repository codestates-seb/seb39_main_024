package com.codestates.flyaway.domain.board.service;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.board.repository.BoardRepository;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import com.codestates.flyaway.web.board.dto.BoardDto;
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
    public BoardResponseDto update(BoardDto.Update updateDto) {

        final Board board = boardRepository.getReferenceById(updateDto.getBoardId());
        board.update(updateDto.getTitle(), updateDto.getContent());

        return boardToBoardResponse(board);
    }

    @Transactional
    public BoardResponseDto read(Long boardId) {

        Board board = findById(boardId);
        board.addViewCount();

        return boardToBoardResponse(board);
    }

    public Page<Board> readAll(int page, int size) {

        PageRequest boardPage = PageRequest.of(page - 1, size, Sort.by("id").descending());

        return boardRepository.findAll(boardPage);
    }

    @Transactional
    public void delete(Long boardId) {

        Board board = findById(boardId);
        boardRepository.delete(board);
    }

    public Board findById(Long boardId) {

        return boardRepository.findById(boardId).orElseThrow(() ->
                new BusinessLogicException(ARTICLE_NOT_FOUND));
    }
}