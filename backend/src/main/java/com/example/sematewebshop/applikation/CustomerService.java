package com.example.sematewebshop.applikation;

import com.example.sematewebshop.domain.Address;
import com.example.sematewebshop.domain.Cart;
import com.example.sematewebshop.domain.Customer;
import com.example.sematewebshop.model.CustomerRegDTO;
import com.example.sematewebshop.persistenz.CartRepository;
import com.example.sematewebshop.persistenz.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final CartRepository cartRepo;
    private final PasswordEncoder passwordEncoder;

    public void registerCustomer(CustomerRegDTO dto) throws IllegalArgumentException {
        try {
            if (customerRepo.findByEmail(dto.getEmail()).isPresent()) {throw new IllegalStateException("Email already registered");}
            Address address = new Address(
                    dto.getStreet(),
                    dto.getNumber(),
                    dto.getCity(),
                    dto.getZipCode()
            );
            Cart cart = new Cart();
            Customer newCustomer = new Customer(
                    dto.getCustomerName(),
                    dto.getEmail(),
                    dto.getPhoneNumber(),
                    address
            );
            newCustomer.setPassword(passwordEncoder.encode(dto.getPassword()));
            newCustomer.setCart(cart); // Direkt einen leeren Warenkorb für den neuen Kunden anlegen
            customerRepo.save(newCustomer);

        } catch (IllegalStateException e) {
            System.out.println("Error. Could not register Customer" + "/nError Note: " + e);
        }
    }
    public boolean loginCustomer(String email, String rawPassword){
        Optional<Customer> customer = customerRepo.findByEmail(email);
        if(customer.isEmpty()){throw new IllegalStateException("Customer not found");}
        return passwordEncoder.matches(rawPassword, customer.get().getPassword());
    }

    public void forgotPassword(String email) {
        Optional<Customer> customerOpt = customerRepo.findByEmail(email);
        if (customerOpt.isEmpty()) {throw new IllegalArgumentException("Customer not found with email: " + email);}
        // TODO: implementieren, Mail verschicken, etc.
        System.out.println("Passwort-Zurücksetzung vorbereitet für: " + email);
    }


    public void updateProfile(Long customerId, CustomerRegDTO updatedData) {
        Customer existingData = customerRepo.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Address address = new Address(updatedData.getStreet(), updatedData.getNumber(), updatedData.getCity(), updatedData.getZipCode());
        existingData.setCustomerName(updatedData.getCustomerName());
        existingData.setEmail(updatedData.getEmail());
        existingData.setPassword(updatedData.getPassword());
        existingData.setPhoneNumber(updatedData.getPhoneNumber());
        existingData.setAddress(address);
        customerRepo.save(existingData);
    }
    public Customer viewProfile(Long customerId) {
        return customerRepo.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

}