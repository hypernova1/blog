package org.blog.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.blog.api.web.payload.AuthDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 9:39 PM
 */

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("로그인 테스트")
    void login() throws Exception {
        AuthDto.LoginRequest loginRequest = new AuthDto.LoginRequest();
        loginRequest.setEmail("hypemova@gmail.com");
        loginRequest.setPassword("1111");
        String json = objectMapper.writeValueAsString(loginRequest);
        mockMvc.perform(post("/v1/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("이메일 중복 확인")
    void check_email() throws Exception {
        String email = "chtlstjd01@gmail.com";
        mockMvc.perform(get("/v1/api/auth/email/" + email))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입")
    void join() throws Exception {
        AuthDto.JoinRequest request = new AuthDto.JoinRequest();
        request.setEmail("chtlstjd01@gmail.com");
        request.setUsername("melchor");
        request.setPassword("1111");
        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/v1/api/auth/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

    }

}