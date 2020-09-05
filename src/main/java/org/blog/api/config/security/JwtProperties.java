package org.blog.api.config.security;

/**
 * Created by melchor
 * Date: 2020/09/05
 * Time: 12:26 AM
 */
public class JwtProperties {

    public static final String SECRET = "melchor";
    public static final int EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
