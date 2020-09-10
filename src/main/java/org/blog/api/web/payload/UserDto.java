package org.blog.api.web.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * Created by melchor
 * Date: 2020/09/08
 * Time: 10:08 PM
 */
public class UserDto {

    @Getter @Setter
    @ToString
    public static class ModifyRequest {

        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @Getter @Setter
    public static class InfoResponse {
        @NotBlank
        private Long id;
        @NotBlank
        private String email;
        @NotBlank
        private String username;
    }

}
