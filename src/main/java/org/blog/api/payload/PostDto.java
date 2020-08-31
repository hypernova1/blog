package org.blog.api.payload;

import lombok.Data;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:43 PM
 */
public class PostDto {

    @Data
    public static class ResponseList {
        private Long id;
        private String title;
        private String writer;
        private String regDate;
    }

}
