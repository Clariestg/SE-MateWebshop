package com.example.sematewebshop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private float price;

    public OrderItem(Product product, int quantity, float price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}

