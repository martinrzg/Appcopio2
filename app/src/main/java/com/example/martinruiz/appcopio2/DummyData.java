package com.example.martinruiz.appcopio2;

import com.example.martinruiz.appcopio2.model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 03/11/2017.
 */

public class DummyData {

    public static Map<String, Product> getDummyData() {
        Product product = new Product("7622210099655", "Oreo","Galletas", "alimentos",0, "https://ramumine.files.wordpress.com/2014/04/oreo.jpg");
        Product product2 = new Product("097339000061", "Valentina","Salsa", "alimentos",0,"https://mexgrocer.hk/wp-content/uploads/2016/12/mexgrocer-salsa-valentina.png");
                Map<String, Product> dummyData = new HashMap<>();
        dummyData.put("7622210099655", product);
        dummyData.put("097339000061",product2);

        return dummyData;
    }
}
