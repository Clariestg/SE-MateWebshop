package com.example.sematewebshop.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long customerID; //auto generated!
    private final String role = "customer";
    private String customerName;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private int phoneNumber; //null weglassen

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Customer(String customerName, String firstname, String lastname, String password, String email, int phoneNumber, Address address) {
        this.customerName = customerName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.customerName = this.firstname + " " + this.lastname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public Customer (){}

    public void changeEmail(String newEmail) {
        if (!newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.email = newEmail;
    }

}
