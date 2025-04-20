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
        private Long InvoiceId;
        private LocalDateTime issueDate;

        @OneToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;

        @OneToOne
        @JoinColumn(name = "order_id")
        private Order order;
        private float totalAmount;

        @Enumerated(EnumType.STRING)
        private PaymentStatus paymentStatus; //PAID, UNPAID, FAILED
}


/*public class Invoice1 {
        private int BillingID;
        private Customer Customer;

        private BillingHistory billingHistory;
}*/
