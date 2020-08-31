package org.blog.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:50 PM
 */
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("게시글 리스트 가져오기")
    void get_post_list() throws Exception {

        mockMvc.perform(get("/v1/api/post")
                .param("page", "1")
                .param("size", "10")
        )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}