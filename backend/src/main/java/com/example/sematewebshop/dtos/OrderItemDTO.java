package com.example.sematewebshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemDTO {
    private Long OrderItemId;
    private String productName;
    private int quantity;
    private float unitPrice;
}
