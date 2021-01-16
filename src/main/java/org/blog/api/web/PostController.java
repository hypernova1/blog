package org.blog.api.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.blog.api.config.security.AuthUser;
import org.blog.api.domain.Account;
import org.blog.api.service.PostService;
import org.blog.api.web.payload.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by melchor
 * Date: 2020/08/30
 * Time: 10:13 PM
 */
@RestController
@Api(value = "PostController V1")
@RequestMapping("/v1/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ApiOperation("게시글 목록 조회")
    @GetMapping("/{categoryName}")
    public ResponseEntity<?> getPostList(
            @PathVariable String categoryName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            String keyword) {
        List<PostDto.ListResponse> list = postService.getList(categoryName, page, size, keyword);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("게시글 등록")
    @PostMapping
    public ResponseEntity<?> registerPost(@RequestBody PostDto.RegisterRequest request, @AuthUser Account account) {
        Long id = postService.register(request, account);
        if (id == null) return ResponseEntity.badRequest().build();

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("게시글 조회")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        PostDto.DetailResponse postDto = postService.getDetail(id);
        return ResponseEntity.ok(postDto);
    }

    @ApiOperation("게시글 수정")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long id,
            @RequestBody PostDto.UpdateRequest request,
            @AuthUser Account account) {
        PostDto.DetailResponse postDto = postService.update(id, request, account);
        return ResponseEntity.ok(postDto);
    }

    @ApiOperation("게시글 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, @AuthUser Account account) {
        postService.delete(id, account);
        return ResponseEntity.ok().build();
    }

}
