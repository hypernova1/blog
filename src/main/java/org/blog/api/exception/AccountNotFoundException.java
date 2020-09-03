package org.blog.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 9:26 PM
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String email) {
        super(email + " is not exist");
    }
}
