package com.example.martinruiz.appcopio2;

import com.example.martinruiz.appcopio2.model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 03/11/2017.
 */

public class DummyData {

    public Map<String, Object> getDummyData() {
        Product product = new Product("7622210099655", "Oreo", "Galletas", "alimentos", 2, R.mipmap.oreo);

        Map<String, Object> dummyData = new HashMap<>();
        dummyData.put("7622210099655", product);

        return dummyData;
    }
}
