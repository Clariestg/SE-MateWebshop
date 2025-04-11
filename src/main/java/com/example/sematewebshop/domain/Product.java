//Product-class is part of Catalog-Context
package com.example.sematewebshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import static com.example.sematewebshop.domain.ProductStatus.*;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB übernimmt das Hochzählen der ID
    private Long productID;
    private String productName;
    private String productDescription;
    private float productPrice;
    private int productQuantity;
    private LocalDateTime productCreationDate; //Nützlich?

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory; //YERBA, CONTAINER, STRAW, CUP, TEAPOT
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus; //AVAILABLE, NOT_AVAILABLE

    public Product(String productName, String productDescription, float productPrice, int productQuantity, LocalDateTime productCreationDate, ProductCategory productCategory, ProductStatus productStatus) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCreationDate = productCreationDate;
        this.productCategory = productCategory;
        this.productStatus = productStatus;
    }

    protected Product() {}

    //getter and setter wegen Lombok schon vorhanden
    public float calculateTotalPrice(){
        return productPrice * productQuantity;
    }

//Verfügbarkeit
    public boolean isAvailableQuantity() {return productQuantity>0;}
    public boolean isAvailableStatus(){return this.productStatus==AVAILABLE;}
    //Logik, sodass ProductStatus je nach Quantität aktualisiert wird z.B. nach einer Bestellung
    public ProductStatus updateProductStatus(){
        if (productQuantity > 0) {this.productStatus = AVAILABLE; return this.productStatus;}
        else {this.productStatus = NOT_AVAILABLE;return this.productStatus;}
    }
    //Nach einer erfolgreichen Bestellung oder beim Starten eines Kaufprozesses
    public void decreaseProductQuantity(int itemQuantity){
        this.productQuantity -= itemQuantity;
    }
    //Auffüllen des Bestands - nur vom Admin!
    public void increaseProductQuantity(int itemQuantity){
        this.productQuantity += itemQuantity;
    }
    //Bestimmte Bestandsmenge überprüfen
    public boolean compareToAvailableQuantity(int itemQuantity){
        return this.productQuantity >= itemQuantity;
    }

}
