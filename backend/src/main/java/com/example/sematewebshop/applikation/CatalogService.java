//Catalog bzw. ProductServices
package com.example.sematewebshop.applikation;

import com.example.sematewebshop.domain.Product;
import com.example.sematewebshop.domain.ProductCategory;
import com.example.sematewebshop.model.ProductDetailDTO;
import com.example.sematewebshop.model.ProductOverviewDTO;
import com.example.sematewebshop.persistenz.ProductRepository;
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
        Optional<Product> product = productRepo.findByProductName(newProductName);
        if (product.isPresent()) {
            throw new IllegalArgumentException("Product name is not available");
        }
        return true;
    }

    //Anzeige-Methoden für Controller
    public ProductDetailDTO  viewProductDetails (Long productId){
        Product product = productRepo.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return new ProductDetailDTO(
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getProductImageUrl()
        );
    }

    public List<ProductOverviewDTO> viewAllProducts () {
        return productRepo.findAll().stream()
                .map(product -> new ProductOverviewDTO(
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
    public List<Product> viewProductByPrice ( float price){
        return productRepo.findAllByProductPrice(price);
    }


    //POST bzw. Request Methoden für Controller
    public Product createProduct (Product product){
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

}
