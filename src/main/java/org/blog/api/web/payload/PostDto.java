package org.blog.api.web.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:43 PM
 */
public class PostDto {

    @Getter @Setter
    public static class ListResponse {
        private Long id;
        private String title;
        private String writer;
        private String regDate;
    }

    @Getter @Setter
    public static class DetailResponse {
        private Long id;
        private String title;
        private String content;
        private String writer;
        private String regDate;
    }

    @Getter @Setter
    @ToString
    public static class RegisterRequest {
        private String title;
        private String content;
    }

    @Getter @Setter
    public static class UpdateRequest {
        private String title;
        private String content;
    }
}
