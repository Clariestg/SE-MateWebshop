package com.example.sematewebshop.dtos;

import com.example.sematewebshop.entities.OrderStatus;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderOverviewDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private float totalPrice;
}
