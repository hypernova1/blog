package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.domain.Post;
import org.blog.api.exception.PostNotFoundException;
import org.blog.api.repository.PostRepository;
import org.blog.api.web.payload.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:32 PM
 */

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository posts;
    private final ModelMapper modelMapper;

    public Page<Post> getList(int page, int size) {
        PageRequest pagination = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdDate");
        return posts.findAll(pagination);
    }

    public Long register(PostDto.RegisterRequest request) {
        Post post = modelMapper.map(request, Post.class);
        Post savedPost = posts.save(post);
        return savedPost.getId();
    }

    public PostDto.DetailResponse get(Long id) {
        Post post = posts.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return modelMapper.map(post, PostDto.DetailResponse.class);
    }

    @Transactional
    public PostDto.DetailResponse update(Long id, PostDto.UpdateRequest request) {
        Post savedPost = posts.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        savedPost.update(request);
        return modelMapper.map(savedPost, PostDto.DetailResponse.class);
    }

    public void delete(Long id) {
        posts.deleteById(id);
    }
}
