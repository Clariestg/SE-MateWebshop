package com.example.sematewebshop.model;

import com.example.sematewebshop.domain.OrderItem;
import com.example.sematewebshop.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDetailDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private float totalPrice;
    private List<OrderItemDTO> items;
}
