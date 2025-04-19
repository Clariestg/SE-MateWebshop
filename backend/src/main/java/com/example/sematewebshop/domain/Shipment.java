package com.example.sematewebshop.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shippment_id")
    private Long id;
    private LocalDateTime shipmentDepartureDate;
    private LocalDateTime shipmentArrivalDate;
    @OneToOne
    private Order order;
    private String shippers;
    @Column(unique = true)
    private String trackingNumber;
    @ManyToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;
}


