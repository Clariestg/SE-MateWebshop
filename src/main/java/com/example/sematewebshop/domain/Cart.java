//Fürs „später kaufen“
package com.example.sematewebshop.domain;

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
    private Long cartID;
    @OneToOne
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
    // Mengensumme aller Items im Cart
    //...

}

