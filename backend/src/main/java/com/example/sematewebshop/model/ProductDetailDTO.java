package com.example.sematewebshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDetailDTO {
    private String productName;
    private String productDescription;
    private float productPrice;
    private String productImageUrl;
}
