package com.example.sematewebshop.entities;

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
    private Long adminId;

    @Column(updatable = false)
    private final String role = "admin";
    @Column(length = 50)
    private String name;
    private String password;
    @Column(unique = true)
    private String email;

    protected Admin() {}

    public void changeEmail(String newEmail) {
        if (!newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.email = newEmail;
    }
}
