package com.codestates.flyaway.domain.boardimage.repository;

import com.codestates.flyaway.domain.boardimage.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {
}
