package com.example.sematewebshop.praesentation;

import com.example.sematewebshop.domain.Product;
import com.example.sematewebshop.applikation.CatalogService;
import com.example.sematewebshop.model.ProductDetailDTO;
import com.example.sematewebshop.model.ProductOverviewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/* Wir benötigen auch Mapping für konkret einen Produkt
-> Nachdem man auf den Produktname geklickt hat sollte ein neues Fenster sich öffnen
    mit mehr Details und Warenkorb- und Kaufbutton
@RequestMapping("/api/produktId")
*/

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CatalogService catalogService;
    public ProductController(CatalogService catalogService) {this.catalogService = catalogService;}


    @GetMapping("/products/overview")
    public List<ProductOverviewDTO> viewAllProducts() { //DTO fürs Best Practice
        return catalogService.viewAllProducts();
    }
    @GetMapping
    public List<Product> viewProductsByCategory(String category) {return catalogService.viewProductsByCategory(category);}
    @GetMapping
    public List<Product> viewProductsByName(String productName) {return catalogService.viewProductsByName(productName);}
    @GetMapping
    public List<Product> viewProductByPrice(float price) {return catalogService.viewProductByPrice(price);}

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductDetails(@PathVariable Long productId){
        return ResponseEntity.ok(catalogService.viewProductDetails(productId));
    }

    @PostMapping //ResponseEntity
    public ResponseEntity<Product> createProduct(@RequestBody Product product) { //Produkt hat 2 Konstruktoren, passende wird automatische gewählt
        Product createdProduct = catalogService.createProduct(product);
        return ResponseEntity.ok(createdProduct); //ResponseEntity.status(HttpStatus.CREATED).body(createdProduct)
    }
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        Product updatedProduct = catalogService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        catalogService.deleteProduct(productId);
        return ResponseEntity.noContent().build();  // Status 204, ohne Content, weil erfolgreich gelöscht
    }

}
