package org.blog.api.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.blog.api.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 9:21 PM
 */

@RestController
@Api(value = "AccountController V1")
@RequestMapping("/v1/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

}
