package com.codestates.flyaway.web.category;

import com.codestates.flyaway.domain.category.entity.Category;
import com.codestates.flyaway.domain.category.service.CategoryService;
import com.codestates.flyaway.global.dto.MultiResponseDto;
import com.codestates.flyaway.global.dto.SingleResponseDto;
import com.codestates.flyaway.web.category.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto.CategoryResponseDto create(@Validated @RequestBody CategoryDto.CreateCategory createCategoryDto) {

        return categoryService.create(createCategoryDto);
    }

    @PatchMapping("/{categoryId}")
    public CategoryDto.CategoryResponseDto update(@PathVariable("categoryId") Long categoryId,
                                 @RequestBody CategoryDto.UpdateCategory updateCategoryDto) {

        updateCategoryDto.setCategoryId(categoryId);

        return categoryService.update(updateCategoryDto);
    }

    @GetMapping 
    public List<CategoryDto.MultiCategoryDto> readCategories(Pageable pageable) {

        return categoryService.readAll(pageable);
    }

    @DeleteMapping("/{categoryId}")
    public HttpStatus deleteCategory(@PathVariable("categoryId") Long categoryId) {

        categoryService.delete(categoryId);

        return HttpStatus.NO_CONTENT;
    }
}
