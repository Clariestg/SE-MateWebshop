package com.example.sematewebshop.controllers;

import com.example.sematewebshop.services.BillingService;
import com.example.sematewebshop.entities.PaymentMethod;
import com.example.sematewebshop.dtos.InvoiceDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/order/billing")
@AllArgsConstructor
public class BillingController {

    private final BillingService billingService;

    //Zahlungsmethode
    @PostMapping("/order/{orderId}/payment-method")
    public ResponseEntity<Void> selectPaymentMethod(@PathVariable Long orderId, @RequestBody PaymentMethod paymentMethod) {
        billingService.selectPaymentMethod(orderId, paymentMethod);
        return ResponseEntity.ok().build();
    }
    //Zahlen
    @PostMapping("/customer/{customerId}/order/{orderId}/pay")
    public ResponseEntity<String> payOrder(@PathVariable Long customerId, @PathVariable Long orderId) {
        billingService.payOrder(customerId, orderId);
        return ResponseEntity.ok("Order successfully paid and invoice created.");
    }
    //Eine Rechnung anzeigen
    @GetMapping("/invoice/order/{orderId}")
    public ResponseEntity<InvoiceDTO> viewInvoice(@PathVariable Long orderId) {
        return ResponseEntity.ok(billingService.viewInvoice(orderId));
    }
    //Rechnungshistorie
    @GetMapping("/invoices/customer/{customerId}")
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices(@PathVariable Long customerId) {
        return ResponseEntity.ok(billingService.getAllInvoices(customerId));
    }
}
