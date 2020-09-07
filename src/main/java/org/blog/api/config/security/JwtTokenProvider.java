package org.blog.api.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by melchor
 * Date: 2020/09/07
 * Time: 8:40 PM
 */

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-in-ms}")
    private int jwtExpirationInMs;

    public String generationToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expired = new Date(now.getTime() + jwtExpirationInMs);

        return JWT.create()
                .withSubject(userPrincipal.getEmail())
                .withIssuedAt(now)
                .withExpiresAt(expired)
                .sign(Algorithm.HMAC512(jwtSecret.getBytes()));
    }

    public String getEmailFromJwt(String token) {
        String TOKEN_PREFIX = "Bearer ";
        return JWT.require(Algorithm.HMAC512(jwtSecret.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return true;
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return false;
    }


}
