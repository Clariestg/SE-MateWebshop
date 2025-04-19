package com.example.sematewebshop.praesentation;

import com.example.sematewebshop.applikation.FulfillmentService;
import com.example.sematewebshop.domain.Address;
import com.example.sematewebshop.model.ShipmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fulfillment")
@RequiredArgsConstructor
public class ShipmentController {

    private final FulfillmentService fulfillmentService;

    //Versanddienstleister z.B. DHL, Express, etc. anzeigen
    @GetMapping("/shipping-options")
    public ResponseEntity<List<String>> getShippers() {
        return ResponseEntity.ok(fulfillmentService.getShipperOptions());
    }
    //Lieferadresse festlegen
    @PostMapping("/order/{orderId}/address")
    public ResponseEntity<Void> setShippingAddress(@PathVariable Long orderId, @RequestBody Address address) {
        fulfillmentService.selectShippingAddress(orderId, address);
        return ResponseEntity.ok().build();
    }
    //Versand starten
    @PostMapping("/order/{orderId}/ship")
    public ResponseEntity<String> shipOrder(@PathVariable Long orderId) {
        fulfillmentService.createShipment(orderId);
        fulfillmentService.setShippedStatus(orderId);
        return ResponseEntity.ok("Shipment created and order marked as SHIPPED");
    }
    //Versandstatus anzeigen
    @GetMapping("/order/{orderId}/track")
    public ResponseEntity<ShipmentDTO> trackShipment(@PathVariable Long orderId) {
        return ResponseEntity.ok(fulfillmentService.trackShipment(orderId));
    }
}
