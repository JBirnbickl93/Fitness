package org.birnbickl.fitness.user.controller;


import jakarta.validation.Valid;
import org.birnbickl.fitness.user.entity.UserEntity;
import org.birnbickl.fitness.user.service.UserService;
import org.birnbickl.fitness.user.dto.request.UserLoginRequest;
import org.birnbickl.fitness.user.dto.request.UserRegistrationRequest;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    private final UserService userService;

    public UserAuthController(UserService userService){
        this.userService = userService;
    }

    // Registrierungs-Methode
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationRequest user){
        UserEntity newUser = userService.createUser(user.getEmail(), user.getPassword(), user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    // Login-Methode
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody UserLoginRequest user){
        String token = userService.loginUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
