package com.codestates.flyaway.web.board;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.board.service.BoardService;
import com.codestates.flyaway.global.dto.MultiResponseDto;
import com.codestates.flyaway.global.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.codestates.flyaway.web.board.dto.BoardDto.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity createArticle(@Validated @RequestBody Create createDto){

        BoardResponseDto created = boardService.create(createDto);

        return new ResponseEntity(new SingleResponseDto(created), HttpStatus.CREATED);
    }

    @PatchMapping("/{boardId}/update")
    public ResponseEntity updateArticle(@PathVariable("boardId") long boardId,
                                        @RequestBody Update updateDto) {

        BoardResponseDto updated = boardService.update(updateDto);

        return new ResponseEntity(new SingleResponseDto(updated), HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity readArticle(@PathVariable("boardId") long boardId) {

        BoardResponseDto read = boardService.read(boardId);

        return new ResponseEntity(new SingleResponseDto(read), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity readAllArticle(@RequestParam int page,
                                         @RequestParam int size) {

        Page<Board> boardPage = boardService.readAll(page, size);
        List<Board> board = boardPage.getContent();
        List<MultiBoardDto> responses = MultiBoardDto.boardsToResponses(board);

        return new ResponseEntity(new MultiResponseDto<>(responses, boardPage), HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteArticle(@PathVariable("boardId") long boardId) {

        boardService.delete(boardId);

        return new ResponseEntity(HttpStatus.OK);
    }
}