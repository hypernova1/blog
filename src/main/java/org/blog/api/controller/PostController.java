package org.blog.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by melchor
 * Date: 2020/08/30
 * Time: 10:13 PM
 */
@RestController
@Api(value = "PostController V1")
@RequestMapping("/v1/api/post")
public class PostController {

    @ApiOperation(value = "post list", notes = "게시글 목록 가져오기")
    @GetMapping
    public ResponseEntity<?> getPostList() {
        return null;
    }

}
