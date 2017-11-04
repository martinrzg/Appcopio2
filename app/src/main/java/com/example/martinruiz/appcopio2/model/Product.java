package com.example.martinruiz.appcopio2.model;

/**
 * Created by Erik on 03/11/2017.
 */

public class Product {

    private String barcode;
    private String name;
    private String type;
    private String category;
    private int quantity;
    private int photoURl;

    public Product() {
    }

    public Product(String barcode, String name, String type, String category, int quantity, int photoURl) {
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.category = category;
        this.quantity = quantity;
        this.photoURl = photoURl;
    }


    public int getPhotoURl() {
        return photoURl;
    }

    public void setPhotoURl(int photoURl) {
        this.photoURl = photoURl;
    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
