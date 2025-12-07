package org.birnbickl.fitness.user;

import org.birnbickl.fitness.api.JwtService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService, jwtService){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Methode um User zu erstellen bzw. Registrierung eines Nutzers
    public UserEntity createUser(String email, String password, String name){
        if (userRepo.findByEmail(email).isPresent()){
            throw new UserNameAlreadyExistsException("Email existiert bereits!");
        } else {
            String encPassoword = passwordEncoder.encode(password);
            UserEntity user = new UserEntity(email, encPassoword, name);
            UserEntity savedUser = userRepo.save(user);
            return savedUser;
        }
    }

    public String loginUser(String email, String password){
        Optional<UserEntity> userOptional = userRepo.findByEmail(email);

        if (userOptional.isEmpty()){
            throw new InvalidCredentialsExpception("Invalid Credentials!");
        }

        UserEntity user = userOptional.get();
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new InvalidCredentialsException("Invalid Credentials!");
        }
        return jwtService.generateToken(user);
    }

}