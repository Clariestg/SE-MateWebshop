package com.example.sematewebshop.services;

import com.example.sematewebshop.entities.Address;
import com.example.sematewebshop.entities.Order;
import com.example.sematewebshop.entities.OrderStatus;
import com.example.sematewebshop.entities.Shipment;
import com.example.sematewebshop.dtos.ShipmentDTO;
import com.example.sematewebshop.repositories.AddressRepository;
import com.example.sematewebshop.repositories.OrderRepository;
import com.example.sematewebshop.repositories.ShipmentRepository;
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
    private final AddressRepository addressRepo;

    //Adresse auswählen
    public void selectShippingAddress(Long orderId, Address address) {
        Address savedAddress = addressRepo.save(address);
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new IllegalStateException("Order not Found"));
        Shipment shipment = new Shipment();
        shipment.setOrder(order);
        shipment.setShippingAddress(savedAddress);
        shipmentRepo.save(shipment);
    }


    public List<String> getShippingOptions() { //Überflüssig fürs Prototyp
        return List.of("STANDARD", "EXPRESS", "OVERNIGHT");
    }

    //Sendung verfolgen
    public ShipmentDTO trackShipment(Long orderId) {
        Shipment shipment = shipmentRepo.findByOrderOrderId(orderId).orElseThrow(() -> new IllegalArgumentException("No shipment found for order"));
        return new ShipmentDTO(
                shipment.getShipmentId(),
                shipment.getShippers(),
                shipment.getTrackingNumber(),
                shipment.getShipmentDepartureDate(),
                shipment.getShipmentArrivalDate()
        );
    }

    public void createShipment(Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        Shipment shipment = shipmentRepo.findByOrder(order) // Shipment sollte mit Adresse schon existieren
                .orElseThrow(() -> new IllegalArgumentException("Shipment must be created with shipping address first"));
        if (shipment.getTrackingNumber() != null) {throw new IllegalArgumentException("Shipment already completed for this order");}
        
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
