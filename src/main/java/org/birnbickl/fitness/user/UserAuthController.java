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

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistration user){
        UserEntity newUser = userService.createUser(user.getEmail(), user.getPassword(), user.getNickName());
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");

    }
}
