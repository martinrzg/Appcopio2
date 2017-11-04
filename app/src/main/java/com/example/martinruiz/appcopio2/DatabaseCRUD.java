package com.example.martinruiz.appcopio2;

import android.util.Log;

import com.example.martinruiz.appcopio2.model.CollectionCenters;
import com.example.martinruiz.appcopio2.model.CollectionCentersInfo;
import com.example.martinruiz.appcopio2.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by Erik on 03/11/2017.
 */

public class DatabaseCRUD {

    private final static String PARENT = "CollectionCenters";

    public static void createCollectionCenter(final DatabaseReference mDatabase, final CollectionCentersInfo collectionCentersInfo) {
        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get Collection Center value
                        if (dataSnapshot.hasChild(PARENT)) {

                            CollectionCenters collectionCenters = dataSnapshot.getValue(CollectionCenters.class);

                            // [START_EXCLUDE]
                            if (collectionCenters == null) {

                                // CollectionCenters is null, error out
                                Log.e(TAG, "Collection Centers is unexpectedly null");
                            } else {
                                // Write new Board
                                newCollectionCenter(mDatabase, collectionCenters.getCollectionCenters(), collectionCentersInfo);
                            }
                        } else {
                            //Initialize boards if is empty
                            Map<String, Object> map = new HashMap<>();
                            newCollectionCenter(mDatabase, map, collectionCentersInfo);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });
    }

    private static void newCollectionCenter(DatabaseReference mDatabase, Map<String, Object> collectionCenters, CollectionCentersInfo collectionCentersInfo) {
        //Generate key and add it to the Colllection Center
        String key = mDatabase.child(PARENT).push().getKey();
        collectionCentersInfo.setId(key);

        //Insert the new board to the boards HashMap
        collectionCenters.put(key, collectionCentersInfo);

        mDatabase.child(PARENT).setValue(collectionCenters);
    }

    public static void creteProduct(final DatabaseReference mDatabase, final Product product, final String collectionCenterId) {
        mDatabase.child("CollectionCenters").child(collectionCenterId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get BoardContent value
                        CollectionCentersInfo collectionCentersInfo = dataSnapshot.getValue(CollectionCentersInfo.class);

                        if (collectionCentersInfo == null) {
                            // Note is null, error out
                            //If id is wrong could get this
                            Log.e(TAG, "CollectionCentersInfo is unexpectedly null");
                        } else {
                            //Send the data to add the note ot the database.
                            newProduct(mDatabase, product, collectionCenterId, collectionCentersInfo);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });
    }

    private static void newProduct(DatabaseReference mDatabase, Product product, String collectionCenterId, CollectionCentersInfo collectionCentersInfo) {
        //Get the key for the new note


        if (collectionCentersInfo.getProducts() == null) {


            Map<String, Object> products = new HashMap();                            //If the product is empty initialize the HashMap for the notes.
            products.put(product.getBarcode(), product);                                                 //Add th new product to the HashMap
            collectionCentersInfo.setProducts(products);                                         //Add the product HashMap to the boardContent
            mDatabase.child(PARENT).child(collectionCenterId).setValue(collectionCentersInfo);      //Update the data of the database
        } else {
                                                        //Add the id to the product object.
            collectionCentersInfo.getProducts().put(product.getBarcode(), product);                               //Add th new product to the HashMap
            mDatabase.child(PARENT).child(collectionCenterId).setValue(collectionCentersInfo);      //Update the data of the database
        }
    }
}
