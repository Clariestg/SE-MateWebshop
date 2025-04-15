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
    private Long orderId;
    private LocalDateTime orderDate;
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderStatus status; //PENDING, PROCESSING, PAID, SHIPPED, CANCELLED
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
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

