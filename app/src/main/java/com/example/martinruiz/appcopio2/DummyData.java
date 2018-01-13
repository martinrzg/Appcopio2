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
        Product product3 = new Product("7501055332175", "Coca-cola","Refresco", "alimentos",0,"http://1vze7o2h8a2b2tyahl3i0t68.wpengine.netdna-cdn.com/wp-content/uploads/2015/09/chronology10.jpg");
        Product product4 = new Product("7501013122039", "Jumex mango","Jugo", "alimentos",0,"https://www.dollartree.com/assets/product_images_2016/styles/xlarge/107938.jpg");

        Product product5 = new Product("7502223775039", "Karo Maple","Miel", "alimentos",0,"http://4.bp.blogspot.com/_IXvn2K5fi28/TIPhSYpFCMI/AAAAAAAAAFQ/dRmJtKoc-hY/s1600/Karo+Maple+Syrup.JPG");
        Map<String, Product> dummyData = new HashMap<>();
        dummyData.put("7622210099655", product);
        dummyData.put("097339000061",product2);
        dummyData.put("7501055332175",product3);
        dummyData.put("7501013122039",product4);
        dummyData.put("7502223775039",product5);
        return dummyData;
    }
}
