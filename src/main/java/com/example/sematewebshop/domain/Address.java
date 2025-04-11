package com.example.sematewebshop.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AddressId;
    private String street;
    private String number; //in case: 23a
    private String city;
    private int zip_code;

    public Address(String street, String number, String city, int zip_code) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.zip_code = zip_code;
    }
    public Address(){}
}
