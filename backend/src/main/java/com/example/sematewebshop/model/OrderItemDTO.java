package com.example.sematewebshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemDTO {
    private String productName;
    private int quantity;
    private float unitPrice;
}
