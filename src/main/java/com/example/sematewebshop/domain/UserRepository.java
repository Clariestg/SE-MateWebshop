package com.example.sematewebshop.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    void save(User newUser);
}

