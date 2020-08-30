package org.blog.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 500, message = "Internal Server Error!"),
            @ApiResponse(code = 404, message = "Not Found!")
    })
    @GetMapping
    public ResponseEntity<?> getPostList() {
        return null;
    }

}
