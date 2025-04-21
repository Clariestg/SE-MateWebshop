package com.example.sematewebshop.repositories;

import com.example.sematewebshop.entities.Cart;
import com.example.sematewebshop.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart(Cart cart);
}
