package com.example.sematewebshop.applikation;

import com.example.sematewebshop.domain.Invoice;
import com.example.sematewebshop.domain.Order;
import com.example.sematewebshop.domain.PaymentMethod;
import com.example.sematewebshop.persistenz.CustomerRepository;
import com.example.sematewebshop.persistenz.InvoiceRepository;
import com.example.sematewebshop.persistenz.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingService {
    private final OrderRepository orderRepo;
    private final InvoiceRepository invoiceRepo;
    private final CustomerRepository customerRepo;

    //z.B. PayPal, Karte etc.
    public void selectPaymentMethod(Long orderId, PaymentMethod method) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setPaymentMethod(method);
        orderRepo.save(order);
    }

}
