package com.example.sematewebshop.applikation;


import com.example.sematewebshop.domain.*;
import com.example.sematewebshop.model.OrderDetailDTO;
import com.example.sematewebshop.model.OrderItemDTO;
import com.example.sematewebshop.model.OrderOverviewDTO;
import com.example.sematewebshop.persistenz.CartRepository;
import com.example.sematewebshop.persistenz.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final CartRepository cartRepo;
    private final OrderRepository orderRepo;

    //Kaufprozess für ein oder mehrere Produkte starten
    //Orderprozess geht bis der "Bezahlen"-Button gedrückt wird
    public boolean placeOrder(Long customerId){
        Cart cart = cartRepo.findByCustomer_CustomerId(customerId).orElseThrow(()-> new IllegalArgumentException("Cart not Found"));
        Customer customer = cart.getCustomer();

        if(cart.getCartItems().isEmpty()){throw new IllegalStateException("Cart is Empty");}

        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus(OrderStatus.PENDING); //Auf Zahlung wird noch gewartet

        for (CartItem cartItem : cart.getCartItems()) {
            Product product = cartItem.getProduct();
            if (!product.compareToAvailableQuantity(cartItem.getQuantity())) {
                throw new IllegalStateException("Not enough stock for product: " + product.getProductName());
            }
            product.decreaseProductQuantity(cartItem.getQuantity());
            product.updateProductStatus();
            OrderItem orderItem = new OrderItem(product, cartItem.getQuantity(), product.getProductPrice());
            newOrder.addOrderItem(orderItem);
        }
        newOrder.setOrderTotalPrice(newOrder.getTotal());
        orderRepo.save(newOrder);
        return true;
    }

    public List<OrderOverviewDTO> viewOrders(Long customerId) {
        List<Order> orders = orderRepo.findAllByCustomer_CustomerId(customerId);
        return orders.stream()
                .map(order -> new OrderOverviewDTO(
                        order.getOrderId(),
                        order.getOrderDate(),
                        order.getStatus(),
                        order.getTotal()
                ))
                .collect(Collectors.toList());
    }

    public OrderDetailDTO viewOrderDetails(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new IllegalArgumentException("Order not Found"));

        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getProduct().getProductName(),
                        item.getQuantity(),
                        item.getProduct().getProductPrice()
                )).toList();
        return new OrderDetailDTO(
                order.getOrderId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getOrderTotalPrice(),
                itemDTOs
        );
    }

    public void cancelOrder(Long orderId){
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new IllegalArgumentException("Order not Found"));
        if(order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.DELIVERED){
            throw new IllegalStateException("Order cannot be cancelled at this stage");
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);
    }

    public OrderStatus trackOrderStatus(Long orderId){
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return order.getStatus();
    }
}
