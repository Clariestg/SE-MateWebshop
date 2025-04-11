package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
