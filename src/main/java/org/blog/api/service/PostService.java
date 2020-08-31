package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.domain.Post;
import org.blog.api.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:32 PM
 */

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository posts;

    public Page<Post> getList(int page, int size) {
        PageRequest pagination = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdDate");
        return posts.findAll(pagination);
    }

}
