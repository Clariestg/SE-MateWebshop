package com.example.sematewebshop.applikation;

import com.example.sematewebshop.domain.*;
import com.example.sematewebshop.model.InvoiceDTO;
import com.example.sematewebshop.persistenz.CartRepository;
import com.example.sematewebshop.persistenz.InvoiceRepository;
import com.example.sematewebshop.persistenz.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillingService {
    private final OrderRepository orderRepo;
    private final InvoiceRepository invoiceRepo;
    private final CartRepository cartRepo;

    //z.B. PayPal, Karte etc.
    public void selectPaymentMethod(Long orderId, PaymentMethod method) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setPaymentMethod(method);
        orderRepo.save(order);
    }

    public void payOrder(Long customerId, Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        if (!order.getCustomer().getCustomerId().equals(customerId)) {throw new IllegalStateException("Order does not belong to this customer");}

        if (order.getStatus() != OrderStatus.PENDING) {throw new IllegalStateException("Order is already paid or cancelled");} //If: Processing, Shipped, Delivered or Cancelled

        // Bestellung als bezahlt markieren
        order.setStatus(OrderStatus.PROCESSING); //Processing statt Paid
        orderRepo.save(order);

        // Rechnung erzeugen und speichern
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setCustomer(order.getCustomer());
        invoice.setIssueDate(LocalDateTime.now());
        invoice.setTotalAmount(order.getOrderTotalPrice());
        invoice.setPaymentStatus(PaymentStatus.PAID);
        invoiceRepo.save(invoice);

        // Warenkorb leeren
        Cart cart = cartRepo.findByCustomer_CustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        cart.clearCart();
        cartRepo.save(cart);
    }

    public InvoiceDTO viewInvoice(Long orderId) {
        Invoice invoice = invoiceRepo.findByOrderOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found for this order"));
        return new InvoiceDTO(
                invoice.getInvoiceId(),
                invoice.getIssueDate(),
                invoice.getTotalAmount(),
                invoice.getPaymentStatus()
        );
    }

    public List<InvoiceDTO> getAllInvoices(Long customerId) {
        List<Invoice> invoices = invoiceRepo.findByCustomer_CustomerId(customerId);
        return invoices.stream()
                .map(invoice -> new InvoiceDTO(
                        invoice.getInvoiceId(),
                        invoice.getIssueDate(),
                        invoice.getTotalAmount(),
                        invoice.getPaymentStatus()
                ))
                .collect(Collectors.toList());
    }


}
