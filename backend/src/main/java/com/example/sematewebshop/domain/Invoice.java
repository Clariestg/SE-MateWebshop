package com.example.sematewebshop.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "invoice_id")
        private Long InvoiceID;
        @Column(nullable = false)
        private LocalDateTime issueDate;

        @OneToOne
        @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;

        @OneToOne
        @JoinColumn(name = "order_id", nullable = false)
        private Order order;

        @Column(nullable = false)
        private float totalAmount;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private PaymentStatus paymentStatus; //PAID, UNPAID, FAILED
}


/*public class Invoice1 {
        private int BillingID;
        private Customer Customer;

        private BillingHistory billingHistory;
}*/
