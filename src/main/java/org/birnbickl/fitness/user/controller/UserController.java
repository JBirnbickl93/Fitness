package org.birnbickl.fitness.user.controller;

import org.birnbickl.fitness.user.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final CustomUserDetailsService userDetailsService;

    public UserController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/api/user/me")
    public ResponseEntity<String> showCredentials(Authentication authentication) {
        String username = userDetailsService.getUsername(authentication.getName());
        return ResponseEntity.ok("Hello " + username + "!");
    }
}
