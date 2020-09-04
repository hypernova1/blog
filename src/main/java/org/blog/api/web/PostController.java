package org.blog.api.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.blog.api.domain.Post;
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
@CrossOrigin("http://localhost:5000")
public class PostController {

    private final PostService postService;

    @ApiOperation("게시글 목록 조회")
    @GetMapping
    public ResponseEntity<?> getPostList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            String keyword) {
        List<PostDto.ListResponse> list = postService.getList(page, size, keyword);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("게시글 등록")
    @PostMapping
    public ResponseEntity<?> registerPost(@RequestBody PostDto.RegisterRequest request) {
        Long id = postService.register(request);
        if (id == null) return ResponseEntity.badRequest().build();

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("게시글 조회")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        PostDto.DetailResponse postDto = postService.get(id);
        return ResponseEntity.ok(postDto);
    }

    @ApiOperation("게시글 수정")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostDto.UpdateRequest request) {
        PostDto.DetailResponse postDto = postService.update(id, request);
        return ResponseEntity.ok(postDto);
    }

    @ApiOperation("게시글 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }

}
