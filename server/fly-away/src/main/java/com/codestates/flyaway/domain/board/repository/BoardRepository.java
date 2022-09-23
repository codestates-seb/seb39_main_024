package com.codestates.flyaway.domain.board.repository;

import com.codestates.flyaway.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByCategoryId(Long categoryId, Pageable pageable);
}
