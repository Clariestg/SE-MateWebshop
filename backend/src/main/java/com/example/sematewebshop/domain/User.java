package com.example.sematewebshop.domain;

import lombok.Getter;
import lombok.Setter;

public class User {
    private String role; //Enum daraus machen?
    @Getter
    @Setter
    private String email;
    public User(String role, String hashed) {this.role = role;}
    private String username;
    private String hashedPassword;

    public boolean checkPassword(String rawPassword) {
        return BCrypt.chechpw(rawPassword, this.hashedPassword);
    }

    public void changeEmail(String newEmail) {
        if (!newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.email = newEmail;
    }

    public static User create(String username, String rawPassword) {
        String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        return new User(username, hashed);
    }


}
