package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.domain.Account;
import org.blog.api.domain.Category;
import org.blog.api.domain.Post;
import org.blog.api.exception.CategoryNotFoundException;
import org.blog.api.exception.PostNotFoundException;
import org.blog.api.repository.CategoryRepository;
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
    private final CategoryRepository categories;
    private final ModelMapper modelMapper;
    private final TagService tagService;

    public List<PostDto.ListResponse> getList(String categoryName, int page, int size, String keyword) {
        Category category = categories.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));
        PageRequest pagination = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdDate");
        List<Post> postList = posts.findByKeyword(pagination, category, keyword);
        return postList.stream()
                .map(post ->  modelMapper.map(post, PostDto.ListResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long register(PostDto.RegisterRequest request, Account account) {
        Category category = categories.findByName(request.getCategoryName())
                .orElseThrow(() -> new CategoryNotFoundException(request.getCategoryName()));
        tagService.findOrCreate(request.getTags());
        Post post = modelMapper.map(request, Post.class);
        post.setCategory(category);
        Post savedPost = posts.save(post);
        savedPost.setWriter(account);
        return savedPost.getId();
    }

    public PostDto.DetailResponse getDetail(Long id) {
        Post post = posts.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return modelMapper.map(post, PostDto.DetailResponse.class);
    }

    @Transactional
    public PostDto.DetailResponse update(Long id, PostDto.UpdateRequest request, Account account) {
        tagService.findOrCreate(request.getTags());
        Category category = categories.findByName(request.getCategoryName())
                .orElseThrow(() -> new CategoryNotFoundException(request.getCategoryName()));
        Post savedPost = posts.findByIdAndWriter(id, account).orElseThrow(() -> new PostNotFoundException(id));
        savedPost.update(request);
        savedPost.setCategory(category);
        return modelMapper.map(savedPost, PostDto.DetailResponse.class);
    }

    @Transactional
    public void delete(Long id, Account account) {
        Post savedPost = posts.findByIdAndWriter(id, account).orElseThrow(() -> new PostNotFoundException(id));
        posts.delete(savedPost);
    }

}
