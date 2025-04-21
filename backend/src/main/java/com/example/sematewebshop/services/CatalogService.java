//Catalog bzw. ProductServices
package com.example.sematewebshop.services;

import com.example.sematewebshop.entities.Product;
import com.example.sematewebshop.entities.ProductCategory;
import com.example.sematewebshop.entities.ProductStatus;
import com.example.sematewebshop.dtos.ProductDetailDTO;
import com.example.sematewebshop.dtos.ProductOverviewDTO;
import com.example.sematewebshop.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatalogService {
    private final ProductRepository productRepo;
    private final RequestMappingHandlerAdapter repositoryExporterHandlerAdapter;

    //Geschäftslogik Methoden
    public boolean isProductNameAvailable(String newProductName) {
        Optional<Product> product = productRepo.findByProductNameContainingIgnoreCase(newProductName);
        if (product.isPresent()) {
            throw new IllegalArgumentException("Product name is not available");
        }
        return true;
    }

    //Anzeige-Methoden für Controller
    public ProductDetailDTO  viewProductDetails (Long productId){
        Product product = productRepo.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return new ProductDetailDTO(
                product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getProductImageUrl()
        );
    }

    public List<ProductOverviewDTO> viewAllProducts () {
        return productRepo.findAll().stream()
                .map(product -> new ProductOverviewDTO(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getProductImageUrl()
                ))
                .collect(Collectors.toList());
    }
    public List<Product> viewProductsByCategory (ProductCategory category){
        return productRepo.findAllByProductCategory(category);
    }
    public List<Product> viewProductsByName (String productName){
        return productRepo.findAllByProductName(productName);
    }
    public List<Product> viewProductByPriceIsLess ( float price){
        return productRepo.findAllByProductPriceIsLessThanEqual(price);
    }
    public List<Product> viewProductByPriceIsBetween ( float priceLow, float priceHigh){
        return productRepo.findAllByProductPriceIsBetween(priceLow, priceHigh);
    }

    //POST bzw. Request Methoden für Controller
    public Product createProduct (Product product){
        if(!isProductNameAvailable(product.getProductName())){throw new IllegalStateException("Product name already exists");}
        validateProduct(product);
        return productRepo.save(product);
    }

    //PUT Mapping
    public Product updateProduct (Long id, Product input){
        Product existingProduct = productRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        // Nur die erlaubten Felder updaten
        if (input.getProductDescription() != null) {
            existingProduct.setProductDescription(input.getProductDescription());
        }
        if (input.getProductPrice() > 0) {
            existingProduct.setProductPrice(input.getProductPrice());
        }
        if (input.getProductQuantity() >= 0) {
            existingProduct.setProductQuantity(input.getProductQuantity());
        }
        //Status aktualisieren
        existingProduct.updateProductStatus();
        return productRepo.save(existingProduct);
    }
    //DELETE
    public void deleteProduct (Long productId){
        try {
            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            productRepo.delete(product);
        } catch (Exception e) {
            throw new IllegalArgumentException("Product not found");
        }
    }

    //Validate Zusatzmethode statt DTO
    private void validateProduct(Product product) {
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {throw new IllegalArgumentException("Product name must not be empty");}
        if (product.getProductPrice() <= 0) {throw new IllegalArgumentException("Price must be greater than 0");}
        if (product.getProductQuantity() < 0) {throw new IllegalArgumentException("Quantity cannot be negative");}
        if (product.getProductCategory() == null) {throw new IllegalArgumentException("Product category must be selected");}
        if (product.getProductImageUrl() == null || product.getProductImageUrl().trim().isEmpty()) {throw new IllegalArgumentException("Product image url must not be empty");}
        if (product.getProductQuantity() > 0) {product.setProductStatus(ProductStatus.AVAILABLE);} else {product.setProductStatus(ProductStatus.NOT_AVAILABLE);}
    }
}
