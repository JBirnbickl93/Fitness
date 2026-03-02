package org.birnbickl.fitness.user;

import org.birnbickl.fitness.errorhandling.EmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserDetails loadUserByEmail(String email) throws EmailNotFoundException {

        email = email.trim().toLowerCase();

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("Email not found!"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPasswordHash())
                .authorities("ROLE_" + user.getRole())
                .build();
    }


    public String getUsername(String email) {
        email = email.trim().toLowerCase();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return user.getUsername();
    }

    public String getUsername() {
        return getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            return loadUserByEmail(username);
        } catch (EmailNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }
}
