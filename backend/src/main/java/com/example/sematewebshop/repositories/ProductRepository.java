package com.example.sematewebshop.repositories;

import com.example.sematewebshop.entities.Product;
import com.example.sematewebshop.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, CrudRepository<Product, Long> {

    Optional<Product> findByProductNameContainingIgnoreCase(String name);
    List<Product> findAllByProductCategory(ProductCategory category);
    List<Product> findAllByProductPriceIsLessThanEqual(float price);
    List<Product> findAllByProductPriceIsBetween(float low, float high);
    List<Product> findAllByProductName(String productName);
}
