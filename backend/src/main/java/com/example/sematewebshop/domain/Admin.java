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
    @Column(name = "admin_id")
    private Long adminID;

    @Column(nullable = false, updatable = false)
    private final String role = "admin";
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;

    protected Admin() {}

    public void changeEmail(String newEmail) {
        if (!newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.email = newEmail;
    }
}
