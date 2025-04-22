package com.example.sematewebshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Product = CartItem, aber CartItem enthält ein oder mehr eines Produktes (CartItem hat eine Menge)
@Entity
@Getter
@Setter
@Table(
        name = "cart_item",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "product_id"})
)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public CartItem(){}

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
    public Long getCartItemProductId() {
        return this.product.getProductId();
    }
    // Betragssumme aller Items
    public float getTotal() {
        return (float) this.quantity * this.getProduct().getProductPrice();
    }
}
