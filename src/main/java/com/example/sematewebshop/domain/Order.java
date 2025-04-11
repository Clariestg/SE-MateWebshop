package com.example.sematewebshop.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderStatus status; //PENDING, PROCESSING, PAID, SHIPPED, CANCELLED
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;


}

