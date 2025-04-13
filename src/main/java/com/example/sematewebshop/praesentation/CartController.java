package com.example.sematewebshop.praesentation;

import com.example.sematewebshop.applikation.CartService;
import com.example.sematewebshop.model.CartOverviewDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    //Wenn Kunde auf sein Warenkorb geht sollen alle Produkt im Warenkorb als Übersicht angezeigt werden
    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartOverviewDTO>> viewCart(@PathVariable Long cartId) {
        List<CartOverviewDTO> cartItems = cartService.viewCart(cartId);
        return ResponseEntity.ok(cartItems);
    }
    //Hinzufügen von der Übersicht
    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<Void> addProductToCart(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @RequestParam int quantity)
    {
        cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok().build(); //Kein Inhalt, nur Status
    }

    //Vom Warenkorb oder Produkt-Detail-Übersicht soll genau ein Produkt entfernt werden
    @DeleteMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    //Vom Warenkorb aus, alle Inhalte auf ein Knopfdruck löschen
    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

}