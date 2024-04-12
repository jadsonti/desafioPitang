package com.pitang.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pitang.desafio.model.User;
import com.pitang.desafio.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CurrentUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"Unauthorized - No authentication information available\"}");
        }

        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByLogin(username);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"User not found\"}");
        }

        User user = userOptional.get();
        return ResponseEntity.ok(user);
    }
}
