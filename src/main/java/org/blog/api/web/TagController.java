package org.blog.api.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.blog.api.service.TagService;
import org.blog.api.web.payload.TagDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by melchor
 * Date: 2020/10/02
 * Time: 8:21 PM
 */

@RestController
@RequestMapping("/v1/api/tag")
@Api(value = "TagController V1")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/{tagName}")
    public ResponseEntity<?> findTagList(@PathVariable String tagName) {
        List<TagDto> tagList = tagService.findTagList(tagName);
        return ResponseEntity.ok(tagList);
    }

}
