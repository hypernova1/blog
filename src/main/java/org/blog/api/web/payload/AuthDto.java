package org.blog.api.web.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 9:22 PM
 */
public class AuthDto {

    @Getter @Setter
    public static class LoginRequest {
        @NotBlank
        String email;
        @NotBlank
        String password;
    }

    @Getter @Setter
    public static class LoginResponse {
        private String email;
        private String username;
        private String accessToken;
        private String tokenType = "Bearer";
    }

    @Getter @Setter
    public static class JoinRequest {
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

}
