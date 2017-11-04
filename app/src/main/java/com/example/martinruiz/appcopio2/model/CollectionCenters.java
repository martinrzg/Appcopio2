package com.example.martinruiz.appcopio2.model;

import java.util.Map;

/**
 * Created by Erik on 03/11/2017.
 */

public class CollectionCenters {

    private Map<String, Object> CollectionCenters;


    public CollectionCenters(){

    }

    public CollectionCenters(Map<String, Object> collectionCenters) {
        CollectionCenters = collectionCenters;
    }

    public Map<String, Object> getCollectionCenters() {
        return CollectionCenters;
    }

    public void setCollectionCenters(Map<String, Object> collectionCenters) {
        CollectionCenters = collectionCenters;
    }
}
