package com.team25.sidedish.controller;

import com.team25.sidedish.dto.response.CategoryResponse;
import com.team25.sidedish.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "categories", description = "카테고리 API")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "카테고리 목록 조회",
    description = "카테고리 전체 목록을 조회합니다",
    responses = {
            @ApiResponse(responseCode = "200",
            description = "카테고리 목록 조회 성공",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class))
                    )
            })
    })
    @GetMapping
    public List<CategoryResponse> getCategories() {
        return categoryService.getCategories().stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

}
