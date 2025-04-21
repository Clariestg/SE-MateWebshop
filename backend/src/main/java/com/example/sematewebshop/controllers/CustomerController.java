package com.example.sematewebshop.controllers;

import com.example.sematewebshop.services.CustomerService;
import com.example.sematewebshop.entities.Customer;
import com.example.sematewebshop.dtos.CustomerRegDTO;
import com.example.sematewebshop.dtos.ForgotPasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerCustomer(@RequestBody CustomerRegDTO dto) {
        try {
            Long customerId = customerService.registerCustomer(dto);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Customer registered successfully");
            response.put("customerId", customerId);

            return ResponseEntity.ok(response);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Registration failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestParam String email,
                                                @RequestParam String password) {
        try {
            boolean success = customerService.loginCustomer(email, password);
            return success ? ResponseEntity.ok("Login successful")
                    : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordDTO dto) {
        try{
        // TODO: Implementieren, evtl. Mail senden etc.
            customerService.forgotPassword(dto.getEmail()); //Todo im CustomerService
            return ResponseEntity.ok("Passwort-Zurücksetzung ausgelöst (noch nicht implementiert)");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kunde nicht gefunden: " + e.getMessage());
        }
    }

    @GetMapping("/{customerId}/profile")
    public ResponseEntity<Customer> viewProfile(@PathVariable Long customerId) {
        try {
            Customer customer = customerService.viewProfile(customerId);
            return ResponseEntity.ok(customer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{customerId}/profile")
    public ResponseEntity<String> updateProfile(@PathVariable Long customerId,
                                                @RequestBody CustomerRegDTO updatedData) {
        try {
            customerService.updateProfile(customerId, updatedData);
            return ResponseEntity.ok("Profile updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Update failed: " + e.getMessage());
        }
    }
}
