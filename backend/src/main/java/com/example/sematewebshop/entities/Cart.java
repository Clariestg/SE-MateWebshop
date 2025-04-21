//Fürs „später kaufen“
package com.example.sematewebshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();


    public void clearCart() {
        cartItems.clear();
    }
    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }
    // Betragssumme aller Items im Cart
    public float getTotal(Long cartId) {
        float total = 0;
        for (CartItem cartItem: this.getCartItems()){
            total += cartItem.getQuantity() * cartItem.getProduct().getProductPrice();
        }
        return total;
    }
    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
    }

    // Mengensumme aller Items im Cart
    //...

}

