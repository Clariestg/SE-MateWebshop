package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.Product;
import com.example.sematewebshop.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, CrudRepository<Product, Long> {

    Optional<Product> findByProductName(String name);
    List<Product> findAllByProductCategory(ProductCategory category);
    List<Product> findAllByProductPrice(float price);
    List<Product> findAllByProductName(String productName);
}
