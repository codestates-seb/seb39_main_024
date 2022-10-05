package com.codestates.flyaway.web.board;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.board.service.BoardService;
import com.codestates.flyaway.domain.boardimage.service.BoardImageService;
import com.codestates.flyaway.global.dto.MultiResponseDto;
import com.codestates.flyaway.web.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final BoardImageService boardImageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{categoryId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public BoardDto.BoardResponseDto create(@PathVariable("categoryId") Long categoryId,
                                   @RequestPart(value = "image",required = false) List<MultipartFile> images,
                                   @Validated @RequestPart BoardDto.Create createDto) {

        createDto.setCategoryId(categoryId);

        return boardService.create(images, createDto);
    }

    @PatchMapping("/{boardId}")
    public BoardDto.BoardResponseDto update(@PathVariable("boardId") Long boardId,
                                    @RequestPart(value = "image",required = false) List<MultipartFile> images,
                                    @Validated @RequestPart BoardDto.Update updateDto) {

        updateDto.setBoardId(boardId);

        return boardService.update(images, updateDto);
    }

    @GetMapping("/{boardId}")
    public BoardDto.BoardResponseDto read(@PathVariable("boardId") Long boardId) {

        return boardService.read(boardId);
    }

    @GetMapping("/all")
    public MultiResponseDto readAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC)
                                      Pageable pageable) {

        Page<Board> boardPage = boardService.readAll(pageable);
        List<Board> board = boardPage.getContent();
        List<BoardDto.MultiBoardDto> responses = BoardDto.MultiBoardDto.toResponsesDto(board);

        return new MultiResponseDto<>(responses, boardPage);
    }

    @GetMapping
    public MultiResponseDto readByCategory(@RequestParam Long categoryId, Pageable pageable) {

        Page<Board> categoryPage = boardService.readByCategory(categoryId, pageable);
        List<Board> boards = categoryPage.getContent();
        List<BoardDto.MultiBoardDto> responses = BoardDto.MultiBoardDto.toResponsesDto(boards);

        return new MultiResponseDto<>(responses, categoryPage);
    }

    @DeleteMapping("/{boardId}")
    public HttpStatus delete(@PathVariable("boardId") Long boardId,
                             @RequestBody BoardDto.Delete deleteDto) {

        deleteDto.setBoardId(boardId);
        boardService.delete(deleteDto);

        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/image/{imageId}")
    public String getImage(@PathVariable Long imageId) {

        return boardImageService.getImage(imageId);
    }

    @PostMapping("/{boardId}/like")
    public void doLike(@PathVariable("boardId") Long boardId,
                       @RequestParam Long memberId) {

        boardService.doLike(memberId, boardId);
    }
}