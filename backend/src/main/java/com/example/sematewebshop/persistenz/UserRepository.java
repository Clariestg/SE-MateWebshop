package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    void save(User newUser);
}

