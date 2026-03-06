package org.birnbickl.fitness.user.service;

import org.birnbickl.fitness.security.JwtService;
import org.birnbickl.fitness.errorhandling.*;

import org.birnbickl.fitness.user.entity.UserEntity;
import org.birnbickl.fitness.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Methode um User zu erstellen bzw. Registrierung eines Nutzers
    public UserEntity createUser(String email, String password, String name) {
        email = email.trim().toLowerCase();
        if (userRepo.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email existiert bereits!");
        }

        String encPassword = passwordEncoder.encode(password);
        UserEntity user = new UserEntity(email, encPassword, name);
        UserEntity savedUser = userRepo.save(user);
        return savedUser;
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