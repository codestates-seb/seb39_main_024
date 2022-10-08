package com.codestates.flyaway.domain.board.service;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.board.repository.BoardRepository;
import com.codestates.flyaway.domain.boardimage.repository.BoardImageRepository;
import com.codestates.flyaway.domain.boardimage.service.BoardImageService;
import com.codestates.flyaway.domain.category.entity.Category;
import com.codestates.flyaway.domain.category.service.CategoryService;
import com.codestates.flyaway.domain.likes.Likes;
import com.codestates.flyaway.domain.likes.LikesRepository;
import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.member.service.MemberService;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import com.codestates.flyaway.web.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.codestates.flyaway.global.exception.ExceptionCode.*;
import static com.codestates.flyaway.web.board.dto.BoardDto.*;
import static com.codestates.flyaway.web.board.dto.BoardDto.BoardResponseDto.*;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CategoryService categoryService;
    private final BoardImageService boardImageService;
    private final BoardImageRepository boardImageRepository;
    private final MemberService memberService;
    private final LikesRepository likesRepository;

    @Transactional
    public BoardResponseDto create(List<MultipartFile> images, BoardDto.Create createDto) {

        Category category = categoryService.findById(createDto.getCategoryId());
        Member member = memberService.findById(createDto.getMemberId());
        Board board = new Board(createDto.getTitle(), createDto.getContent());
        board.setCategory(category);
        board.setMember(member);
        boardRepository.save(board);
        boardImageService.saveFiles(images, board);

        return toResponseDto(board);
    }

    @Transactional
    public BoardResponseDto update(List<MultipartFile> images, BoardDto.Update updateDto) {

        Category category = categoryService.findById(updateDto.getCategoryId());
        final Board board = boardRepository.getReferenceById(updateDto.getBoardId());
        if(!Objects.equals(updateDto.getMemberId(), board.getMember().getId())) {
            throw new BusinessLogicException(NOT_AUTHORIZED);
        }
        board.setCategory(category);
        board.update(updateDto.getTitle(), updateDto.getContent());
        boardImageService.updateFiles(images, board);

        return toResponseDto(board);
    }

    @Transactional
    public BoardResponseDto read(Long boardId) {

        Board board = findById(boardId);
        board.addViewCount();

        return toResponseDto(board);
    }

    public List<MultiBoardDto> readAll(Pageable pageable) {

        Page<Board> boardPage = boardRepository.findAll(pageable);
        List<Board> board = boardPage.getContent();

        return MultiBoardDto.toResponsesDto(board);
    }

    public List<MultiBoardDto> readByCategory(Long categoryId, Pageable pageable) {

        Page<Board> boardCategory = boardRepository.findAllByCategoryId(categoryId, pageable);
        List<Board> categories = boardCategory.getContent();

        return MultiBoardDto.toResponsesDto(categories);
    }

    @Transactional
    public void delete(BoardDto.Delete deleteDto) {

        Board board = findById(deleteDto.getBoardId());
        if(!Objects.equals(board.getMember().getId(), deleteDto.getMemberId())) {
            throw new BusinessLogicException(NOT_AUTHORIZED);
        }
        boardImageRepository.deleteAll(boardImageRepository.findAllByBoardId(deleteDto.getBoardId()));
        boardRepository.delete(board);
    }

    public Board findById(Long boardId) {

        return boardRepository.findById(boardId).orElseThrow(() ->
                new BusinessLogicException(ARTICLE_NOT_FOUND));
    }

    @Transactional
    public boolean doLike(Long boardId, Long memberId) {

        boolean likeResult;
        Member member = memberService.findById(memberId);
        Board board = findById(boardId);
        Optional<Likes> savedLikes = likesRepository.findByBoardAndMember(board, member);
        if(savedLikes.isPresent()) {
            likesRepository.findByBoardAndMember(board, member).ifPresent(id -> likesRepository.deleteAll());
            board.dislike();
            likeResult = false;
        }else {
            board.addLikeCount();
            likeResult = true;
            likesRepository.save(Likes.builder()
                    .board(board)
                    .member(member)
                    .build());
        }
        return likeResult;
    }

    public boolean readLike(Long boardId, Long memberId) {

        Member member = memberService.findById(memberId);
        Board board = findById(boardId);
        Optional<Likes> savedLikes = likesRepository.findByBoardAndMember(board, member);
        return savedLikes.isPresent();
    }
}