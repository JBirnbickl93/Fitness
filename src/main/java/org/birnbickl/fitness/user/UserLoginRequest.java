package org.birnbickl.fitness.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginRequest {

    @NotBlank(message ="Email darf nicht leer sein!")
    private String email;

    @NotBlank(message = "Passwort darf nicht leer sein!")
    @Size(min = 8, message = "Passwort muss mindestens 8 Zeichen lang sein!")
    private String password;

    public UserLoginRequest(String email, String password, String nickName) {
        this.email = email;
        this.password = password;
    }

    public UserLoginRequest() {}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {this.password = password;}
}
