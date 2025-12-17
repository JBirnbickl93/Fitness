package org.birnbickl.fitness.user;

import jakarta.persistence.*;

@Entity
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

    public UserEntity(String email, String passwordHash, String name) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
    }

    public UserEntity(){
        super();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getNickName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public boolean isEnabled() {
        return true;
    }
}
