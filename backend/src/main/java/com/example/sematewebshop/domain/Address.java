package com.example.sematewebshop.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id") // saubere DB-Spaltenbenennung (optional)
    private Long addressId;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 10)
    private String number; // z.B. "23a"

    @Column(nullable = false, length = 50)
    private String city;

    @Column(name = "zip_code", nullable = false)
    private int zipCode;

    public Address(String street, String number, String city, int zipCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address() {}

}
