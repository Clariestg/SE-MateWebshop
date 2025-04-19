package com.example.sematewebshop.model;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
public class ShipmentDTO {
    private String shippers;
    private String trackingNumber;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
}
