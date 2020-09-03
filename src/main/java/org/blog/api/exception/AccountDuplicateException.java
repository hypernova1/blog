package org.blog.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 9:35 PM
 */

@ResponseStatus(HttpStatus.CONFLICT)
public class AccountDuplicateException extends RuntimeException {
    public AccountDuplicateException(String email) {
        super(email + " is already exist.");
    }
}
