package org.blog.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by melchor
 * Date: 2020/09/12
 * Tie: 12:58 AM
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotEqualWriterException extends RuntimeException {
    public NotEqualWriterException(Long id, String email) {
        super(id + " post is not owned by " + email);
    }
}
