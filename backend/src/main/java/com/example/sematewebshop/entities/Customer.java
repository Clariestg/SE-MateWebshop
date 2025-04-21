package com.example.sematewebshop.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId; //auto generated!

    @Column(updatable = false)
    private String role = "customer";
    @Column(length = 100)
    private String customerName;
    private String password;
    @Column(unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Customer(String customerName, String password, String email, String phoneNumber, Address address) {
        this.customerName = customerName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public Customer(String customerName, String email, String phoneNumber, Address address) {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Customer (){}

    public void changeEmail(String newEmail) {
        if (!newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.email = newEmail;
    }

}
