package com.example.sematewebshop.dtos;

import com.example.sematewebshop.entities.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class InvoiceDTO {
    private Long invoiceId;
    private LocalDateTime issueDate;
    private double totalAmount;
    private PaymentStatus paymentStatus; //Oder String
}
