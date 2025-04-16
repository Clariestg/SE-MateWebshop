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
    @Column(name = "customer_id")
    private Long customerID; //auto generated!
    @Column(nullable = false, updatable = false)
    private final String role = "customer";
    @Column(nullable = false, length = 100)
    private String customerName;

    @Column(nullable = false, length = 50)
    private String firstname;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private int phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Customer(String firstname, String lastname, String password, String email, int phoneNumber, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.customerName = this.firstname + " " + this.lastname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public Customer(String firstname, String lastname, String email, int phoneNumber, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.customerName = this.firstname + " " + this.lastname;
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
