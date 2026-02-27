package org.birnbickl.fitness.user;

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
        String nickname = userDetailsService.getNickname(authentication.getName());
        return ResponseEntity.ok("Hello " + nickname + "!");
    }
}
