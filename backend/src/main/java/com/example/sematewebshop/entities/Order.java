package com.example.sematewebshop.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders") //order ist ein reserviertes SQL-Schlüsselwort
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id") // Für unidirektionales Mapping
    private List<OrderItem> orderItems = new ArrayList<>();
    private float orderTotalPrice;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; //CREDIT_CARD, PAYPAL, BANK_TRANSFER, SOFORT, CASH_ON_DELIVERY

    public Order(LocalDateTime orderDate, Customer customer, OrderStatus status, List<OrderItem> orderItems) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.status = status;
        this.orderItems = orderItems;
        this.orderTotalPrice = this.getTotal();
        this.paymentMethod = this.getPaymentMethod();
    }

    public void addOrderItem(OrderItem orderItem) {orderItems.add(orderItem);}

    public float getTotal() {
        if(orderItems.isEmpty()){throw new IllegalStateException("Order without OrderItems");}
        float total = 0;
        for (OrderItem orderItem: this.getOrderItems()){
            total += orderItem.getQuantity() * orderItem.getProduct().getProductPrice();
        }
        return total;
    }

}

