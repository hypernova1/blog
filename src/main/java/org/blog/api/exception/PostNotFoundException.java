package org.blog.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by melchor
 * Date: 2020/09/02
 * Time: 4:39 PM
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super(id + "번 글을 찾을 수 없습니다.");
    }
}
