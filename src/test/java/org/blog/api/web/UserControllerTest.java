package org.blog.api.web;

import org.blog.api.BasedTest;
import org.blog.api.web.payload.AuthDto;
import org.blog.api.web.payload.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by melchor
 * Date: 2020/09/10
 * Time: 9:17 AM
 */
class UserControllerTest extends BasedTest {

    @Test
    @DisplayName("회원 정보 변경")
    void modify_info() throws Exception {
        AuthDto.LoginRequest loginRequest = new AuthDto.LoginRequest();
        loginRequest.setEmail("hypemova@gmail.com");
        loginRequest.setPassword("2222");
        String json = objectMapper.writeValueAsString(loginRequest);

        MvcResult result = mockMvc.perform(post("/v1/api/auth/login").content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        AuthDto.LoginResponse loginResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AuthDto.LoginResponse.class);
        String accessToken = loginResponse.getAccessToken();
        String tokenType = loginResponse.getTokenType();

        UserDto.ModifyRequest modifyRequest = new UserDto.ModifyRequest();
        modifyRequest.setUsername("samchan");
        modifyRequest.setPassword("2222");

        String json2 = objectMapper.writeValueAsString(modifyRequest);

        mockMvc.perform(put("/v1/api/user")
                .header("Authorization", tokenType + " " + accessToken)
                .content(json2))
                .andDo(print())
                .andExpect(status().isOk());
    }

}