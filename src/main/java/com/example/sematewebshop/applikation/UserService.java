package com.example.sematewebshop.applikation;

import com.example.sematewebshop.domain.User;
import com.example.sematewebshop.persistenz.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.map(user -> user.checkPassword(password))
                .orElse(false);
    }

    public boolean register(String username, String password) {
        Optional<User> existing = userRepository.findByUsername(username);
        if (existing.isPresent()) {
            return false;
        }

        User newUser = User.create(username, password);
        userRepository.save(newUser);
        return true;
    }

}
