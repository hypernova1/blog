package org.blog.api.web.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.blog.api.domain.Tag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

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
        private Set<Tag> tags;
    }

    @Getter @Setter
    @ToString
    public static class RegisterRequest {
        @NotBlank
        private String title;
        @NotNull
        private String content;
        private Set<Tag> tags;
    }

    @Getter @Setter
    public static class UpdateRequest {
        @NotBlank
        private String title;
        @NotNull
        private String content;
        private Set<Tag> tags;
    }
}
