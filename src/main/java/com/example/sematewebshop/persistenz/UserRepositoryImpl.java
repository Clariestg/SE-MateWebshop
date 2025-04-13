package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.User;
import com.example.sematewebshop.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JpaUserRepository jpaRepo;

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepo.findByUsername(username);
    }

    @Override
    public void save(User user) {
        jpaRepo.save(user); // jpaRepo = Spring Data JPA
    }

}

