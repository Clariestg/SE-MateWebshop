package com.example.sematewebshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Product = CartItem, aber CartItem enthält ein oder mehr eines Produktes (CartItem hat eine Menge)
@Entity
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(nullable = false)
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
        return this.product.getProductID();
    }
    // Betragssumme aller Items
    public float getTotal() {
        return (float) this.quantity * this.getProduct().getProductPrice();
    }
}
