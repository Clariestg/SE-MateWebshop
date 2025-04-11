package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
