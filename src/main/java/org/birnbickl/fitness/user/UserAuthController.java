package org.birnbickl.fitness.user;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    private final UserService userService;

    public UserAuthController(UserService userService){
        this.userService = userService;
    }
}
