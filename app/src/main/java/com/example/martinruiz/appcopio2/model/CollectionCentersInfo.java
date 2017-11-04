package com.example.martinruiz.appcopio2.model;

import java.util.Map;

/**
 * Created by Erik on 03/11/2017.
 */

public class CollectionCentersInfo {

    private String id;
    private String location;
    private Map<String, Product> products;

    public CollectionCentersInfo() {
    }

    public CollectionCentersInfo(String location, Map<String, Product> products) {
        this.location = location;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }
}
