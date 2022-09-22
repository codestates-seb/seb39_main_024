package com.codestates.flyaway.domain.comment.repository;

import com.codestates.flyaway.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
