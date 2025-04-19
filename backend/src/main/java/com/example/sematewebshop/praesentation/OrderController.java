package com.example.sematewebshop.praesentation;

import com.example.sematewebshop.applikation.OrderService;
import com.example.sematewebshop.domain.OrderStatus;
import com.example.sematewebshop.model.OrderDetailDTO;
import com.example.sematewebshop.model.OrderOverviewDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //Bestellung aufgeben
    @PostMapping("/place/{customerId}")
    public ResponseEntity<String> placeOrder(@PathVariable Long customerId) {
        boolean isOrderPlaced = orderService.placeOrder(customerId);
        if (isOrderPlaced) {
            return ResponseEntity.ok("Order placed successfully.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place order.");
    }

    //Kunden ruft die Bestellungsseite auf - alle Bestellungen anzeigen als Dashboard
    @GetMapping("/view/{customerId}")
    public ResponseEntity<List<OrderOverviewDTO>> viewOrders(@PathVariable Long customerId) {
        List<OrderOverviewDTO> orderOverview = orderService.viewOrders(customerId);
        return ResponseEntity.ok(orderOverview);
    }

    //Kunde fordert mehr Informationen über eine bestimmte Bestellung
    @GetMapping("/details/{orderId}")
    public ResponseEntity<OrderDetailDTO> viewOrderDetails(@PathVariable Long orderId) {
        OrderDetailDTO orderDetail = orderService.viewOrderDetails(orderId);
        return ResponseEntity.ok(orderDetail);
    }

    //Bestellung kann storniert werden, solange es nicht schon geliefert wird oder geliefert ist
    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        try {
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok("Order cancelled successfully.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Bestellungsstatus verfolgen/ aktualisieren - ist egl schon in Detailansicht enthalten
    @GetMapping("/status/{orderId}")
    public ResponseEntity<OrderStatus> trackOrderStatus(@PathVariable Long orderId) {
        OrderStatus status = orderService.trackOrderStatus(orderId);
        return ResponseEntity.ok(status);
    }
}
