package com.example.sematewebshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerRegDTO { //Auch wenn nicht alles geändert werden soll, müssen alle Daten übergeben werden
    private String customerName;
    private String password;
    private String email;
    private String phoneNumber;

    private String street;
    private String number;
    private String city;
    private int zipCode;
}
