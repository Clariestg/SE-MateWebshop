package com.example.sematewebshop.model;

import com.example.sematewebshop.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
public class InvoiceDTO {
    private Long invoiceId;
    private LocalDateTime issueDate;
    private double totalAmount;
    private PaymentStatus paymentStatus; //Oder String
}
