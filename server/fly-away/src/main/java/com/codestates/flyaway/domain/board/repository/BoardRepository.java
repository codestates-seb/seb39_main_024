package com.codestates.flyaway.domain.board.repository;

import com.codestates.flyaway.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
