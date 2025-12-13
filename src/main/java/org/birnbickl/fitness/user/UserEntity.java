package org.birnbickl.fitness.user;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true , nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;


    // Anzeigename
    private String name;

    @Column(nullable = false)
    private String role = "USER";

    private Integer heightCm;
    private Integer birthYear;
    private String gender; // "male, female, other"

    public UserEntity(String email, String password, String name) {
        this.email = email;
        this.passwordHash = password;
        this.name = name;
    }

    public UserEntity(){
        super();
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isEnabled() {
        return false;
    }
}
