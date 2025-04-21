package com.example.sematewebshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartOverviewDTO {
    private Long cartItemId;
    private String cartItemName;
    private float cartItemPrice;
    private int cartItemQuantity;
    private String cartItemImageUrl;
}
