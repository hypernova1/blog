package org.blog.api.web;

import org.blog.api.BasedTest;
import org.blog.api.repository.post.PostRepository;
import org.blog.api.web.payload.AuthDto;
import org.blog.api.web.payload.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:50 PM
 */

class PostControllerTest extends BasedTest {

    @Autowired
    PostRepository posts;

    private String token;

//    @BeforeEach
//    @DisplayName("테스트 데이터 삽입")
//    void insert_post_list() {
//        Post post1 = Post.builder().title("title1").content("content1").build();
//        Post post2 = Post.builder().title("title2").content("content1").build();
//        Post post3 = Post.builder().title("title3").content("content1").build();
//        Post post4 = Post.builder().title("title4").content("content1").build();
//        posts.saveAll(Arrays.asList(post1, post2, post3, post4));
//    }

    @BeforeEach
    @Test
    public void login() throws Exception {
        AuthDto.LoginRequest loginRequest = new AuthDto.LoginRequest();
        loginRequest.setEmail("hypemova@gmail.com");
        loginRequest.setPassword("1111");
        String json = objectMapper.writeValueAsString(loginRequest);

        MvcResult result = mockMvc.perform(post("/v1/api/auth/login").content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        AuthDto.LoginResponse loginResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AuthDto.LoginResponse.class);
        String accessToken = loginResponse.getAccessToken();
        String tokenType = loginResponse.getTokenType();

        this.token = tokenType + " " + accessToken;
    }

    @Test
    @DisplayName("게시글 리스트 가져오기")
    void get_post_list() throws Exception {
        mockMvc.perform(get("/v1/api/post")
                .param("page", "1")
                .param("size", "10")
                .param("keyword", "1")
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시글 등록")
    void register_post() throws Exception {
        PostDto.RegisterRequest request = new PostDto.RegisterRequest();
        request.setTitle("title");
        request.setContent("content");
        String json = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/v1/api/post")
                .header("Authorization", token)
                .content(json))
            .andDo(print())
            .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("게시글 수정")
    void update_post() throws Exception {
        String existPostContent = posts.findById(1L).get().getContent();
        PostDto.UpdateRequest request = new PostDto.UpdateRequest();
        request.setTitle("update title");
        request.setContent("update content");
        String json = objectMapper.writeValueAsString(request);
        MvcResult result = mockMvc.perform(put("/v1/api/post/1")
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        PostDto.DetailResponse post = objectMapper.readValue(result.getResponse().getContentAsString(), PostDto.DetailResponse.class);
        assertNotEquals(post.getContent(), existPostContent);
    }

    @Test
    @DisplayName("게시글 삭제")
    void delete_post() throws Exception {
        mockMvc.perform(delete("/v1/api/post/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}