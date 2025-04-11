package com.example.sematewebshop.domain;

public class User {
    private String role; //Enum daraus machen?
    private String email;
    public User(String role) {this.role = role;}

    public void changeEmail(String newEmail) {
        if (!newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.email = newEmail;
    }
    
}
