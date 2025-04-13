//Product-class is part of Catalog-Context
package com.example.sematewebshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;
import static com.example.sematewebshop.domain.ProductStatus.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB übernimmt das Hochzählen der ID
    private Long productID;
    private String productName;
    private String productDescription;
    private float productPrice;
    private int productQuantity;
    private String productImageUrl;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory; //YERBA, CONTAINER, STRAW, CUP, TEAPOT
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus; //AVAILABLE, NOT_AVAILABLE

    public Product(String productName, String productDescription, float productPrice, int productQuantity, String productCategory, String productImageUrl) {
        //Validierung
        if (productName == null || productName.trim().isEmpty()) {throw new IllegalArgumentException("Product name must not be empty");}
        if (productPrice <= 0) {throw new IllegalArgumentException("Price must be greater than 0");}
        if (productQuantity < 0) {throw new IllegalArgumentException("Quantity cannot be negative");}
        if (productCategory == null || productCategory.trim().isEmpty()) {throw new IllegalArgumentException("Product category must be selected");
        }else{setCategory(productCategory);}
        //ordentliche URL Validierung fehlt noch!
        if(productImageUrl == null || productImageUrl.trim().isEmpty()) {throw new IllegalArgumentException("Product image url must not be empty");}
        //Setting
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        if (productQuantity > 0) {this.productStatus = AVAILABLE;}else{this.productStatus = NOT_AVAILABLE;}
        this.productImageUrl = productImageUrl;

    }
    public Product(String productName, String productDescription, float productPrice, int productQuantity, String productCategory) {
        //Validierung
        if (productName == null || productName.trim().isEmpty()) {throw new IllegalArgumentException("Product name must not be empty");}
        if (productPrice <= 0) {throw new IllegalArgumentException("Price must be greater than 0");}
        if (productQuantity < 0) {throw new IllegalArgumentException("Quantity cannot be negative");}
        if (productCategory == null || productCategory.trim().isEmpty()) {throw new IllegalArgumentException("Product category must be selected");
        }else{setCategory(productCategory);}
        //Setting
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        if (productQuantity > 0) {this.productStatus = AVAILABLE;}else{this.productStatus = NOT_AVAILABLE;}
    }
    protected Product() {}

//Eingabe Validierung
    //Prüfen, ob Angaben stimmen (Optionen) -> YERBA, CONTAINER, STRAW, CUP, TEAPOT
    public void setCategory(String stringCategory) {
        try {this.productCategory = ProductCategory.valueOf(stringCategory.toUpperCase()); //String in Uppercase Form übergeben
        }catch (Exception e) {throw new IllegalArgumentException("Invalid category: " + stringCategory +
                ". Allowed values: YERBA, CONTAINER, STRAW, CUP, TEAPOT");
        }
    }
    //Ob Produktname vergeben ist


//getter and setter wegen Lombok schon vorhanden
    public float calculateTotalPrice(){return productPrice * productQuantity;}

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
