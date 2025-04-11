package com.example.sematewebshop.applikation;

//Entitäten
import com.example.sematewebshop.domain.CartItem;
import com.example.sematewebshop.domain.Cart;
import com.example.sematewebshop.domain.Product;
//Repos
import com.example.sematewebshop.persistenz.CartRepository;
import com.example.sematewebshop.persistenz.CartItemRepository;
import com.example.sematewebshop.persistenz.ProductRepository;
//Andere
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CartService {
    private CartRepository cartRepo;
    private ProductRepository productRepo;
    private CartItemRepository cartItemRepo;

    //Für jede Produktmenge, die zum Warenkorb hinzugefügt wird, wird die Verfügbarkeit überprüft
    //z.B. während durch den Produkt-Katalog gegangen wird
    public void addProductToCart(Long cartId, Long productId, int quantity) {

        Cart cart = cartRepo.findById(cartId) //den Kunden Warenkorb aus der Datenbank holen und speichern
                .orElseThrow(() -> new IllegalArgumentException("Cart not Found")); //Dieser Fehler sollte nicht auftreten, wenn es ein Kunde ist :)
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not Found"));

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
    

}
