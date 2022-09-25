package com.codestates.flyaway.web.category.dto;

import com.codestates.flyaway.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoryDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateCategory {

        @NotBlank
        private String categoryName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateCategory {

        private Long categoryId;
        @NotBlank
        private String categoryName;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryResponseDto {

        private Long categoryId;
        private String categoryName;

        public static CategoryResponseDto toResponseDto(Category category) {

            return new CategoryResponseDto(
                    category.getId(),
                    category.getCategoryName());
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultiCategoryDto {

        private Long categoryId;
        private String categoryName;

        public static List<MultiCategoryDto> toResponsesDto(List<Category> categories) {

            return categories.stream()
                    .map(category -> new MultiCategoryDto(
                            category.getId(),
                            category.getCategoryName()))
                    .collect(Collectors.toList());
        }
    }
}
