package org.blog.api.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.blog.api.web.payload.AuthDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 9:21 PM
 */
@RestController
@Api(value = "AuthController V1")
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto.LoginRequest request) {
        AuthDto.LoginResponse loginDto = authService.login(request);
        return ResponseEntity.ok(loginDto);
    }

    @ApiOperation("회원가입")
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AuthDto.JoinRequest request) {
        Long id = authService.join(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("이메일 중복 확인")
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getEmail(@PathVariable String email) {
        boolean isExistEmail = authService.isExistEmail(email);
        if (isExistEmail) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().build();
    }

}
