package com.pitang.desafio.config;

public class JwtConfig {
    public static final String SECRET = "MySecretKey";
    public static final int EXPIRATION_TIME = 864_000_000; //10 dias
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
