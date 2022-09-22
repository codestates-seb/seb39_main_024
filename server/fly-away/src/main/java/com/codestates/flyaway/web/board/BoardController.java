package com.codestates.flyaway.web.board;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.board.service.BoardService;
import com.codestates.flyaway.global.dto.MultiResponseDto;
import com.codestates.flyaway.global.dto.SingleResponseDto;
import com.codestates.flyaway.web.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity write(@Validated @RequestBody BoardDto.Create createDto){

        BoardDto.BoardResponseDto wrote = boardService.create(createDto);

        return new ResponseEntity(new SingleResponseDto(wrote), HttpStatus.CREATED);
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity update(@PathVariable("boardId") Long boardId,
                                        @RequestBody BoardDto.Update updateDto) {

        BoardDto.BoardResponseDto updated = boardService.update(updateDto);

        return new ResponseEntity(new SingleResponseDto(updated), HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity read(@PathVariable("boardId") Long boardId) {

        BoardDto.BoardResponseDto read = boardService.read(boardId);

        return new ResponseEntity(new SingleResponseDto(read), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity readAll(@RequestParam int page,
                                  @RequestParam int size) {

        Page<Board> boardPage = boardService.readAll(page, size);
        List<Board> board = boardPage.getContent();
        List<BoardDto.MultiBoardDto> responses = BoardDto.MultiBoardDto.boardsToResponses(board);

        return new ResponseEntity(new MultiResponseDto<>(responses, boardPage), HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity delete(@PathVariable("boardId") Long boardId) {

        boardService.delete(boardId);

        return new ResponseEntity(HttpStatus.OK);
    }
}