package org.blog.api.web.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 9:22 PM
 */
public class AuthDto {

    @Getter @Setter
    public static class LoginRequest {
        String email;
        String password;
    }

    @Getter @Setter
    public static class LoginResponse {
        private String email;
        private String username;
    }

    @Getter @Setter
    public static class JoinRequest {
        private String email;
        private String username;
        private String password;
    }

}
