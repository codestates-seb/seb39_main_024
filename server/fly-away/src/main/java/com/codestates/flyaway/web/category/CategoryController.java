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
    public SingleResponseDto create(@Validated @RequestBody CategoryDto.CreateCategory createCategoryDto) {

        CategoryDto.CategoryResponseDto created = categoryService.create(createCategoryDto);

        return new SingleResponseDto(created);
    }

    @PatchMapping("/{categoryId}")
    public SingleResponseDto update(@PathVariable("categoryId") Long categoryId,
                                 @RequestBody CategoryDto.UpdateCategory updateCategoryDto) {

        updateCategoryDto.setCategoryId(categoryId);
        CategoryDto.CategoryResponseDto updated = categoryService.update(updateCategoryDto);

        return new SingleResponseDto<>(updated);
    }

    @GetMapping 
    public MultiResponseDto readCategories(Pageable pageable) {

        Page<Category> categories = categoryService.readAll(pageable);
        List<Category> category = categories.getContent();
        List<CategoryDto.MultiCategoryDto> responses = CategoryDto.MultiCategoryDto.responsesDto(category);

        return new MultiResponseDto<>(responses, categories);
    }

    @DeleteMapping("/{categoryId}")
    public HttpStatus deleteCategory(@PathVariable("categoryId") Long categoryId) {

        categoryService.delete(categoryId);

        return HttpStatus.NO_CONTENT;
    }
}
