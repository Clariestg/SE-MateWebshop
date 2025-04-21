package com.example.sematewebshop.repositories;

import com.example.sematewebshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomer_CustomerId(Long customerId);

}
