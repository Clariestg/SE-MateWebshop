package com.example.sematewebshop.repositories;

import com.example.sematewebshop.entities.Order;
import com.example.sematewebshop.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Optional<Shipment> findByOrder(Order order);
    Optional<Shipment> findByOrderOrderId(Long orderId);
    boolean existsByOrder(Order order);
    boolean existsByTrackingNumber(String trackingNumber);
}
