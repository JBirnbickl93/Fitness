package org.birnbickl.fitness.bootstrap;

import org.birnbickl.fitness.user.repository.UserRepository;
import org.birnbickl.fitness.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataBaseInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.createUser("admin1@web1.de", "adminpassword", "admin1");
        userService.createUser("user1@web1.de", "userpassword", "user1");

    System.out.println("User Data seed initialized.");

    }


}