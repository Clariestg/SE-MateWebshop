//Warenkorb
package com.example.sematewebshop.applikation;

import com.example.sematewebshop.domain.CartItem;
import com.example.sematewebshop.domain.Cart;
import com.example.sematewebshop.domain.Product;
//Repos
import com.example.sematewebshop.model.CartOverviewDTO;
import com.example.sematewebshop.persistenz.CartRepository;
import com.example.sematewebshop.persistenz.CartItemRepository;
import com.example.sematewebshop.persistenz.ProductRepository;
//Andere
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final CartItemRepository cartItemRepo;

    //Für jede Produktmenge, die zum Warenkorb hinzugefügt wird, wird die Verfügbarkeit überprüft
    //z.B. während durch den Produkt-Katalog gegangen wird
    public void addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepo.findById(cartId) //den Kunden Warenkorb aus der Datenbank holen und speichern
                .orElseThrow(() -> new IllegalArgumentException("Cart not Found")); //Dieser Fehler sollte nicht auftreten, wenn es ein Kunde ist :)
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not Found"));
        product.updateProductStatus();
        if(!product.compareToAvailableQuantity(quantity)) { //Es muss geprüft werden, ob die Anzahl verfügbar ist
            throw new IllegalStateException("Not enough product in stock");
        }
        for(CartItem newItem: cart.getCartItems()){
            //Prüfen, ob das Produkt schon im Warenkorb vorhanden ist
            if (Objects.equals(newItem.getCartItemProductId(), productId)) {
                //True: Menge erhöhen
                newItem.increaseQuantity(quantity);
                cartItemRepo.save(newItem); // Speichern des aktualisierten CartItems
                return;
            }
        }
        // Wenn das Produkt noch nicht im Warenkorb ist, neues CartItem hinzufügen
        CartItem newItem = new CartItem(product, quantity);
        cart.addCartItem(newItem); //Zum Warenkorb hinzugefügt
        cartItemRepo.save(newItem);
    }

    public void updateCartItemQuantity(Long cartId, Long productId, int quantity) {
        if(quantity < 0) {throw new IllegalArgumentException("Quantity cannot be negative");}
        else if (quantity == 0) {removeProductFromCart(cartId, productId); return;}
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not Found"));
        CartItem cartItem = cart.getCartItems().stream().filter(item -> item.getCartItemProductId().equals(productId)).findFirst().orElseThrow(() -> new IllegalArgumentException("Product not Found"));
        cartItem.setQuantity(quantity); //überschrieben, neue Gesamtquantität muss eingegeben werden
        cartItemRepo.save(cartItem);
    }

    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not Found"));
        CartItem cartItem = cart.getCartItems().stream()
                        .filter(item -> item.getProduct().getProductId().equals(productId))
                        .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not Found"));
        cart.removeCartItem(cartItem);
        cartItemRepo.delete(cartItem);
    }

    public List<CartOverviewDTO> viewCart(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not Found"));
        return cartItemRepo.findAllByCart(cart).stream()
                .map(cartItem -> new CartOverviewDTO(
                        cartItem.getProduct().getProductName(),
                        cartItem.getProduct().getProductPrice(),
                        cartItem.getQuantity(),
                        cartItem.getProduct().getProductImageUrl()
                ))
                .collect(Collectors.toList());
    }

    public void clearCart(Long cartId){
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not Found"));
        cart.clearCart();
        cartRepo.save(cart);
    }

}
