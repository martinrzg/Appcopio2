package com.example.martinruiz.appcopio2.fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martinruiz.appcopio2.DatabaseCRUD;
import com.example.martinruiz.appcopio2.DummyData;
import com.example.martinruiz.appcopio2.R;
import com.example.martinruiz.appcopio2.activities.BarcodeCaptureActivity;
import com.example.martinruiz.appcopio2.activities.MainActivity;
import com.example.martinruiz.appcopio2.model.CollectionCentersInfo;
import com.example.martinruiz.appcopio2.model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    DatabaseReference mDatabase;

    private static final int RC_BARCODE_CAPTURE = 9001;
    @BindView(R.id.famAddProduct) FloatingActionMenu famAddProduct;
    @BindView(R.id.recyclerViewRegister) RecyclerView recyclerViewRegister;
    RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerAdapter adapter;
    private FirebaseRecyclerOptions<Product> options;
    private Query query;




    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this,view);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        query= FirebaseDatabase.getInstance()
                .getReference()
                .child("userIdDummy").child("CollectionCenters").child(MainActivity.collectionCenterId)
                .child("products")
                .limitToLast(50);


        options = new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(query, Product.class)
                        .build();


        adapter = new FirebaseRecyclerAdapter<Product,ProductViewHolder>(options) {

            @Override
            public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_product, parent, false);

                return new ProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ProductViewHolder holder, int position, Product model) {
                holder.textView.setText(model.getName());
                holder.textViewQty.setText("#"+model.getQuantity());
            }
        };

        query.addChildEventListener(childEventListener);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewRegister.setAdapter(adapter);
        recyclerViewRegister.setLayoutManager(layoutManager);
        adapter.startListening();
        return view;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        public  TextView textView, textViewQty;
        public ProductViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewItemName);
            textViewQty  = itemView.findViewById(R.id.textViewQty);
        }
    }


    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            // ...
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            // ...
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            // ...
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            // ...
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.stopListening();
    }


    @OnClick(R.id.fabBarcode)
    public void fabBarcodeScanner(){

        famAddProduct.close(true);

        Intent intent = new Intent(getActivity(), BarcodeCaptureActivity.class);
        intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
        intent.putExtra(BarcodeCaptureActivity.UseFlash, false);
        startActivityForResult(intent, RC_BARCODE_CAPTURE);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    showDialogAddProduct("Agregar producto","Indique la cantidad", 0, barcode.displayValue);



                    Toast.makeText(getActivity(), barcode.displayValue, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "No barcode captured", Toast.LENGTH_SHORT).show();
                }
            } else {

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showDialogAddProduct(String title, String message, int id, String barcode){

        mDatabase.child("userIdDummy").child("CollectionCenters").child(MainActivity.collectionCenterId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        CollectionCentersInfo collectionCentersInfo = dataSnapshot.getValue(CollectionCentersInfo.class);
                        Boolean hasMap;
                        if(collectionCentersInfo.getProducts() != null){
                            hasMap = true;
                        }else {
                            hasMap = false;
                        }
                        if(hasMap || DummyData.getDummyData().get(barcode) != null)
                        {
                            Product product;
                            if(collectionCentersInfo.getProducts().containsKey(barcode)) {
                                 product = collectionCentersInfo.getProducts().get(barcode);
                                Log.d(TAG, "Entra normal.");
                            }else {
                                product = DummyData.getDummyData().get(barcode);
                                Log.d(TAG, "Entra dummy.");

                            }
                            final AlertDialog dialog = new AlertDialog.Builder(getContext())
                                    .setPositiveButton("Agregar",null)
                                    .setNegativeButton("Cancelar",null)
                                    .create();
                            if(title != null) dialog.setTitle(title);
                            if(message != null) dialog.setMessage(message);
                            View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_product,null);
                            dialog.setView(viewInflated);
                            ImageView imageViewPreviewProduct = viewInflated.findViewById(R.id.imageViewPreviewProduct);
                            TextView textViewProductName = viewInflated.findViewById(R.id.textViewProductName);
                            EditText editTextProductQty = viewInflated.findViewById(R.id.editTextProductQty);
                            textViewProductName.setText(product.getName());
                            Picasso.with(getActivity()).load(product.getPhotoURl()).transform(new CropCircleTransformation()).into(imageViewPreviewProduct);

                            dialog.setOnShowListener(dialogInterface -> {
                                Button buttonPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                Button buttonNegative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                                buttonPositive.setOnClickListener(view -> {
                                    dialog.dismiss();
                                    mDatabase.child("userIdDummy").child("CollectionCenters").child(MainActivity.collectionCenterId).addListenerForSingleValueEvent(
                                            new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    // Get BoardContent value
                                                    CollectionCentersInfo collectionCentersInfo = dataSnapshot.getValue(CollectionCentersInfo.class);

                                                    Product product = new Product();
                                                    if (collectionCentersInfo == null) {
                                                        // Note is null, error out
                                                        //If id is wrong could get this
                                                        Log.e(TAG, "CollectionCentersInfo is unexpectedly null");
                                                    } else {
                                                        //Send the data to add the note ot the database.
                                                        Map<String, Product> map = collectionCentersInfo.getProducts();
                                                        if(map !=null) {

                                                            if (map.containsKey(barcode)) {
                                                                product =  map.get(barcode);

                                                                product.setQuantity(product.getQuantity() + Integer.parseInt(editTextProductQty.getText().toString()));
                                                            }else {
                                                                Map<String, Product> dummy = DummyData.getDummyData();
                                                                product = dummy.get(barcode);
                                                                product.setQuantity(Integer.parseInt(editTextProductQty.getText().toString()));
                                                                map.put(barcode,product);
                                                            }

                                                        }else {
                                                            Map<String, Product> dummy = DummyData.getDummyData();
                                                            product = dummy.get(barcode);
                                                            product.setQuantity(Integer.parseInt(editTextProductQty.getText().toString()));
                                                        }
                                                        DatabaseCRUD.creteProduct(mDatabase.child("userIdDummy"),product, MainActivity.collectionCenterId);
                                                    }

                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                    Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                                                }
                                            });

                                });
                                buttonNegative.setOnClickListener(view -> {dialog.dismiss();});
                            });
                            dialog.create();
                            dialog.show();


                        }else{
                            //TODO: AGREGAR NUEVO PRODUCTO
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });
    }

    @OnClick(R.id.fabManual)
    public void fabManual() {

    }




}
