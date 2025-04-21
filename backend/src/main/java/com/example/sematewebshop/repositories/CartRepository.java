package com.example.sematewebshop.repositories;

import com.example.sematewebshop.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCustomer_CustomerId(Long customerId);
}