package com.example.sematewebshop.controllers;

import com.example.sematewebshop.entities.Product;
import com.example.sematewebshop.services.CatalogService;
import com.example.sematewebshop.entities.ProductCategory;
import com.example.sematewebshop.dtos.ProductDetailDTO;
import com.example.sematewebshop.dtos.ProductOverviewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CatalogService catalogService;
    public ProductController(CatalogService catalogService) {this.catalogService = catalogService;}

    @GetMapping("/products/overview")
    public List<ProductOverviewDTO> viewAllProducts() { //DTO fürs Best Practice
        return catalogService.viewAllProducts();
    }
    @GetMapping("/by-category")
    public List<Product> viewProductsByCategory(@RequestParam ProductCategory category) {return catalogService.viewProductsByCategory(category);}
    @GetMapping("/by-name")
    public List<Product> viewProductsByName(@RequestParam String productName) {return catalogService.viewProductsByName(productName);}

    @GetMapping("/by-price-less")
    public List<Product> viewProductByPriceIsLess(@RequestParam float price) {return catalogService.viewProductByPriceIsLess(price);}
    @GetMapping("/by-price-range")
    public List<Product> viewProductByPriceIsBetween(@RequestParam float priceLow, float priceHigh) {return catalogService.viewProductByPriceIsBetween(priceLow, priceHigh);}

    @GetMapping("/products/{productId}")
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
