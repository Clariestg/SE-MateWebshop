package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
