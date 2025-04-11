package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.Customer;
import com.example.sematewebshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> FindAll();

}
