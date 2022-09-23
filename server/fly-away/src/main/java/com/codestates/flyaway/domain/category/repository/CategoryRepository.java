package com.codestates.flyaway.domain.category.repository;

import com.codestates.flyaway.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
