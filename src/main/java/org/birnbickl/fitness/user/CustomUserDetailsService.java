package org.birnbickl.fitness.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        username = username.trim().toLowerCase();

        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPasswordHash())
                .authorities("ROLE_" + user.getRole())
                .build();
    }


    public String getNickname(String email) {
        email = email.trim().toLowerCase();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return user.getNickName();
    }

    public String getNickname() {
        return getNickname(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
