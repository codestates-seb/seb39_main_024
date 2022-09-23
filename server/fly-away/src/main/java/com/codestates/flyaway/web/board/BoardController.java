package com.codestates.flyaway.web.board;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.board.service.BoardService;
import com.codestates.flyaway.global.dto.MultiResponseDto;
import com.codestates.flyaway.global.dto.SingleResponseDto;
import com.codestates.flyaway.web.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/{categoryId}")
    public SingleResponseDto write(@PathVariable("categoryId") Long categoryId,
                                   @Validated @RequestBody BoardDto.Create createDto){

        createDto.setCategoryId(categoryId);
        BoardDto.BoardResponseDto wrote = boardService.create(createDto);

        return new SingleResponseDto(wrote);
    }

    @PatchMapping("/{boardId}")
    public SingleResponseDto update(@PathVariable("boardId") Long boardId,
                                        @RequestBody BoardDto.Update updateDto) {

        BoardDto.BoardResponseDto updated = boardService.update(updateDto);

        return new SingleResponseDto(updated);
    }

    @GetMapping("/{boardId}")
    public SingleResponseDto read(@PathVariable("boardId") Long boardId) {

        BoardDto.BoardResponseDto read = boardService.read(boardId);

        return new SingleResponseDto(read);
    }

    @GetMapping("/all")
    public MultiResponseDto readAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC)
                                      Pageable pageable) {

        Page<Board> boardPage = boardService.readAll(pageable);
        List<Board> board = boardPage.getContent();
        List<BoardDto.MultiBoardDto> responses = BoardDto.MultiBoardDto.boardsToResponsesDto(board);

        return new MultiResponseDto<>(responses, boardPage);
    }

    @GetMapping
    public MultiResponseDto readByCategory(@RequestParam Long categoryId, Pageable pageable) {

        Page<Board> categoryPage = boardService.readByCategory(categoryId, pageable);
        List<Board> boards = categoryPage.getContent();
        List<BoardDto.MultiBoardDto> responses = BoardDto.MultiBoardDto.boardsToResponsesDto(boards);

        return new MultiResponseDto<>(responses, categoryPage);
    }

    @DeleteMapping("/{boardId}")
    public HttpStatus delete(@PathVariable("boardId") Long boardId) {

        boardService.delete(boardId);

        return HttpStatus.NO_CONTENT;
    }
}