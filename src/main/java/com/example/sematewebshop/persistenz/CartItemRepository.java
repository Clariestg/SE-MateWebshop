package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.Cart;
import com.example.sematewebshop.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByProductId(Long productId);
    List<CartItem> findAllByCart(Cart cart);
}
