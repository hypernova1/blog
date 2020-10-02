package org.blog.api.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.blog.api.service.CategoryService;
import org.blog.api.web.payload.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by melchor
 * Date: 2020/09/20
 * Time: 9:08 PM
 */

@RestController
@Api(value = "PostController V1")
@RequestMapping("/v1/api/post")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @ApiOperation("카테고리 목록 조회")
    @GetMapping
    public ResponseEntity<?> getCategoryList() {
        List<CategoryDto> categories = categoryService.getList();
        return ResponseEntity.ok(categories);
    }

    @ApiOperation("카테고리 등록")
    @PostMapping
    public ResponseEntity<?> registerCategory(CategoryDto categoryDto) {
        Long id = categoryService.register(categoryDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation("카테고리 수정")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, CategoryDto categoryDto) {
        categoryService.update(id, categoryDto);
        return ResponseEntity.ok().build();
    }

}
