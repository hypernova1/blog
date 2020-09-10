package org.blog.api.web.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by melchor
 * Date: 2020/09/08
 * Time: 10:08 PM
 */
public class UserDto {

    @Getter @Setter
    @ToString
    public static class ModifyRequest {
        private String username;
        private String password;
    }

    @Getter @Setter
    public static class InfoResponse {
        private Long id;
        private String email;
        private String username;
    }

}
