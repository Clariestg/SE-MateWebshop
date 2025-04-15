package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
