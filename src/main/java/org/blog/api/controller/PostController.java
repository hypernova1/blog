package org.blog.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.blog.api.domain.Post;
import org.blog.api.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "post list", notes = "게시글 목록 가져오기")
    @GetMapping
    public ResponseEntity<?> getPostList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Post> list = postService.getList(page, size);
        if (list.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

}
