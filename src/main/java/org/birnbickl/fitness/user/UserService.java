package org.birnbickl.fitness.user;

import org.birnbickl.fitness.api.JwtService;
import org.birnbickl.fitness.errorhandling.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Methode um User zu erstellen bzw. Registrierung eines Nutzers
    public UserEntity createUser(String email, String password, String name){
        if (userRepo.findByEmail(email).isPresent()){
            throw new UsernameAlreadyExists("Email existiert bereits!");
        } else {
            String encPassword = passwordEncoder.encode(password);
            UserEntity user = new UserEntity(email, encPassword, name);
            UserEntity savedUser = userRepo.save(user);
            return savedUser;
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) userRepo.findByEmail(email)
        .orElseThrow(()->new UsernameNotFoundException("Username not found!"));
    }


    public String loginUser(String email, String password){
        email = email.trim().toLowerCase();

        UserEntity user = userRepo.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials!"));

        if(!user.isEnabled()){
            throw new InvalidCredentialsException("Invalid Credentials!");
        }


        if(!passwordEncoder.matches(password, user.getPasswordHash())){
            throw new InvalidCredentialsException("Invalid Credentials!");
        }
        return jwtService.generateToken(user);
    }

}