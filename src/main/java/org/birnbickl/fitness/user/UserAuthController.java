package org.birnbickl.fitness.user;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    private final UserService userService;

    public UserAuthController(UserService userService){
        this.userService = userService;
    }

    // Registrierungs-Methode
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistration user){
        UserEntity newUser = userService.createUser(user.getEmail(), user.getPassword(), user.getNickName());
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    // Login-Methode
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequest user){
        String token = userService.loginUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok().body(token);
    }
}
