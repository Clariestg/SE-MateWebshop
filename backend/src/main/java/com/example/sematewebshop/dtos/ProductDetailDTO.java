package com.example.sematewebshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDetailDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private float productPrice;
    private String productImageUrl;
}
