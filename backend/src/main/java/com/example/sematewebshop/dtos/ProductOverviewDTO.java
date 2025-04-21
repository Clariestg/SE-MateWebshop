//Clean Architectur mit klaren Schichtentrennung bedeutet unteranderem DTOs
package com.example.sematewebshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductOverviewDTO {
    private Long productId;
    private String productName;
    private float productPrice;
    private String productImageUrl;
}

