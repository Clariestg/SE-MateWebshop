package com.example.sematewebshop.domain;

//Entwurfsmuster Singleton verwenden?

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long adminID;
    private final String role = "admin";
    private String name;
    private String password;
    private String email;

    protected Admin() {}

    public void changeEmail(String newEmail) {
        if (!newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.email = newEmail;
    }
}
