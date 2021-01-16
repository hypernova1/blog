package org.blog.api.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.blog.api.config.security.AuthUser;
import org.blog.api.config.security.UserPrincipal;
import org.blog.api.domain.Account;
import org.blog.api.service.UserService;
import org.blog.api.web.payload.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 9:21 PM
 */

@RestController
@Api(value = "UserController V1")
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("유저 정보 업데이트")
    @PutMapping
    public ResponseEntity<?> modifyInfo(@RequestBody UserDto.ModifyRequest request, @AuthUser Account account) {
        UserDto.InfoResponse userDto = userService.modifyInfo(request, account);
        return ResponseEntity.ok(userDto);
    }

}
