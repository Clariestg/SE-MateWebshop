//Clean Architectur mit klaren Schichtentrennung bedeutet unteranderem DTOs
package com.example.sematewebshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductOverviewDTO {
    private String productName;
    private float productPrice;
    private String productImageUrl;
}

