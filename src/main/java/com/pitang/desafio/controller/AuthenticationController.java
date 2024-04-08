package com.pitang.desafio.controller;

import com.pitang.desafio.model.AuthenticationRequest;
import com.pitang.desafio.model.User;
import com.pitang.desafio.service.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @PostMapping("/api/signin")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = userService.authenticate(authenticationRequest.getLogin(), authenticationRequest.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        // Use a chave secreta definida nas configurações
        String secretKey = env.getProperty("jwt.secret");

        // Assumindo que a chave secreta é codificada em Base64 nas configurações
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);

        String jwt = Jwts.builder()
                .setSubject(user.getLogin())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(Keys.hmacShaKeyFor(decodedKey)) // Use a chave decodificada
                .compact();

        return ResponseEntity.ok(jwt);
    }
}