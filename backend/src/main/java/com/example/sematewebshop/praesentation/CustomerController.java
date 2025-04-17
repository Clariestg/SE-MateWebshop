package com.example.sematewebshop.praesentation;

import com.example.sematewebshop.applikation.CustomerService;
import com.example.sematewebshop.domain.Customer;
import com.example.sematewebshop.model.CustomerRegDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegDTO dto) {
        try {
            customerService.registerCustomer(dto);
            return ResponseEntity.ok("Customer registered successfully");
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
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
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        // TODO: Implementieren, evtl. Mail senden etc.
        return ResponseEntity.ok(Map.of("message", "Passwort-Zurücksetzung ausgelöst (noch nicht implementiert)"));
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
