package com.pitang.desafio.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    private String login;
    private String password;
}
