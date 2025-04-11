package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}