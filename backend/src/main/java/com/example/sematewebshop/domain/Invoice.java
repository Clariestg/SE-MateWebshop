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
        private Long InvoiceID;
        private LocalDateTime issueDate;
        @OneToOne
        private Customer customer; //Damit alle Rechnungen vom Kunden angezeigt werden können als "BillingHistory"
        @OneToOne
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
