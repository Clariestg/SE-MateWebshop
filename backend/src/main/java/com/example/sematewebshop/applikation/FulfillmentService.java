package com.example.sematewebshop.applikation;

import com.example.sematewebshop.domain.Address;
import com.example.sematewebshop.domain.Order;
import com.example.sematewebshop.domain.OrderStatus;
import com.example.sematewebshop.domain.Shipment;
import com.example.sematewebshop.model.ShipmentDTO;
import com.example.sematewebshop.persistenz.OrderRepository;
import com.example.sematewebshop.persistenz.ShipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FulfillmentService {
    private final OrderRepository orderRepo;
    private final ShipmentRepository shipmentRepo;

    //Adresse auswählen
    public void selectShippingAddress(Long orderId, Address shippingAddress) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        Shipment shipment = shipmentRepo.findByOrder(order).orElseGet(() -> new Shipment());
        shipment.setOrder(order);
        shipment.setShippingAddress(shippingAddress);
        shipmentRepo.save(shipment);
    }

    public List<String> getShippingOptions() { //Überflüssig fürs Prototyp
        return List.of("STANDARD", "EXPRESS", "OVERNIGHT");
    }

    //Sendung verfolgen
    public ShipmentDTO trackShipment(Long orderId) {
        Shipment shipment = shipmentRepo.findByOrderOrderId(orderId).orElseThrow(() -> new IllegalArgumentException("No shipment found for order"));
        return new ShipmentDTO(
                shipment.getShippers(),
                shipment.getTrackingNumber(),
                shipment.getShipmentDepartureDate(),
                shipment.getShipmentArrivalDate()
        );
    }

    public void createShipment(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        if(shipmentRepo.existsByOrder(order)) {throw new IllegalArgumentException("Shipment already exists for this order");}

        Shipment shipment = new Shipment();
        shipment.setOrder(order);
        shipment.setShippers(selectRandomShipper());
        shipment.setTrackingNumber(generateTrackingNumber());
        shipment.setShipmentDepartureDate(LocalDateTime.now());
        shipment.setShipmentArrivalDate(LocalDateTime.now().plusDays(new Random().nextInt(3) + 2)); // 2–4 Tage
        shipmentRepo.save(shipment);
    }
    //Lieferobjekt absenden
    public void setShippedStatus(Long orderId){
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(OrderStatus.SHIPPED);
        orderRepo.save(order);
    }

    public List<String> getShipperOptions() {
        return List.of("DHL", "Hermes", "DPD", "UPS", "SE-Mate Express");
    }

//Weil kein externer Service
    private String selectRandomShipper() {
        List<String> shippers = List.of("DHL", "Hermes", "DPD", "UPS", "SE-Mate Express");
        return shippers.get(new Random().nextInt(shippers.size()));
    }

    private String generateTrackingNumber() {
        String tracking;
        do{
            tracking = "TRK-" + UUID.randomUUID().toString().substring(0,12).toUpperCase();
        }while(shipmentRepo.existsByTrackingNumber(tracking));
        return tracking;
    }
}
