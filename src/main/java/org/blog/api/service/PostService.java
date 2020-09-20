package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.config.security.UserPrincipal;
import org.blog.api.domain.Account;
import org.blog.api.domain.Post;
import org.blog.api.exception.PostNotFoundException;
import org.blog.api.repository.post.PostRepository;
import org.blog.api.web.payload.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    private final TagService tagService;

    public List<PostDto.ListResponse> getList(int page, int size, String keyword) {
        PageRequest pagination = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdDate");
        List<Post> postList = posts.findByKeyword(pagination, keyword);
        return postList.stream()
                .map(post ->  modelMapper.map(post, PostDto.ListResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long register(PostDto.RegisterRequest request, UserPrincipal authUser) {
        tagService.findOrCreate(request.getTags());
        Post post = modelMapper.map(request, Post.class);
        Post savedPost = posts.save(post);
        Account writer = modelMapper.map(authUser, Account.class);
        savedPost.setWriter(writer);
        return savedPost.getId();
    }

    public PostDto.DetailResponse getDetail(Long id) {
        Post post = posts.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return modelMapper.map(post, PostDto.DetailResponse.class);
    }

    @Transactional
    public PostDto.DetailResponse update(Long id, PostDto.UpdateRequest request, UserPrincipal authUser) {
        tagService.findOrCreate(request.getTags());
        Post savedPost = posts.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        savedPost.verifyWriter(authUser);
        savedPost.update(request);
        return modelMapper.map(savedPost, PostDto.DetailResponse.class);
    }

    public void delete(Long id, UserPrincipal authUser) {
        Post savedPost = posts.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        savedPost.verifyWriter(authUser);
        posts.deleteById(id);
    }



}
