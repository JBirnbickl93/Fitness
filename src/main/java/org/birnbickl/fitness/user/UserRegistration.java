package org.birnbickl.fitness.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistration {

    @NotBlank(message ="Email darf nicht leer sein!")
    private String email;

    @NotBlank(message = "Passwort darf nicht leer sein!")
    @Size(min = 8, message = "Passwort muss mindestens 8 Zeichen lang sein!")
    private String password;

    @NotBlank(message = "Username darf nicht leer sein!")
    private String nickName;


    public UserRegistration(String email, String password, String nickName) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public UserRegistration() {}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {this.password = password;}
    public String getNickName() {return nickName;}
    public void setNickName(String nickName) {this.nickName = nickName;}
}
