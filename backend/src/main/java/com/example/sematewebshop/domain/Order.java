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
    @Column(name = "order_id")
    private Long orderId;
    @Column(nullable = false)
    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id") // Für unidirektionales Mapping
    private List<OrderItem> orderItems;
    @Column(nullable = false)
    private float orderTotalPrice;

    public Order(LocalDateTime orderDate, Customer customer, OrderStatus status, List<OrderItem> orderItems) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.status = status;
        this.orderItems = orderItems;
        this.orderTotalPrice = this.getTotal();
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

