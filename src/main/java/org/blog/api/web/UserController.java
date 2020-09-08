package org.blog.api.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.blog.api.config.security.AuthUser;
import org.blog.api.config.security.UserPrincipal;
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

    @PutMapping
    public ResponseEntity<?> modifyInfo(@RequestBody UserDto.ModifyRequest request, @AuthUser UserPrincipal authUser) {
        UserDto.InfoResponse userDto = userService.modifyInfo(request, authUser);
        return ResponseEntity.ok(userDto);
    }

}
