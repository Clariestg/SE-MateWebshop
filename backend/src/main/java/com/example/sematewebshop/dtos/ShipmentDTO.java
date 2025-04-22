package com.example.sematewebshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ShipmentDTO {
    private Long shipmentId;
    private String shippers;
    private String trackingNumber;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
}
