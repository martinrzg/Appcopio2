package com.example.martinruiz.appcopio2.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martinruiz.appcopio2.R;
import com.example.martinruiz.appcopio2.activities.BarcodeCaptureActivity;
import com.example.martinruiz.appcopio2.activities.GenerateQR;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.example.martinruiz.appcopio2.activities.MainActivity;
import com.example.martinruiz.appcopio2.model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KitFragment extends Fragment {
    DatabaseReference mDatabase;

    @BindView(R.id.recyclerViewKitItems)
    RecyclerView recyclerViewKitItems;
    RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerAdapter adapter;
    private FirebaseRecyclerOptions<Product> options;
    private Query query;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RC_BARCODE_CAPTURE = 9001;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public KitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KitFragment newInstance(String param1, String param2) {
        KitFragment fragment = new KitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_kit, container, false);

        ButterKnife.bind(this,rootView);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.tbKit);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Kit");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        query= FirebaseDatabase.getInstance()
                .getReference()
                .child("userIdDummy").child("CollectionCenters").child(MainActivity.collectionCenterId)
                .child("products")
                .limitToLast(50);


        options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(query, Product.class)
                .build();


        adapter = new FirebaseRecyclerAdapter<Product,KitFragment.KitViewHolder>(options) {

            @Override
            public KitFragment.KitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.kit_item, parent, false);

                return new KitFragment.KitViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(KitFragment.KitViewHolder holder, int position, Product model) {
                holder.textView.setText(model.getName());

            }
        };

        query.addChildEventListener(childEventListener);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewKitItems.setAdapter(adapter);
        recyclerViewKitItems.setLayoutManager(layoutManager);
        adapter.startListening();
        return rootView;
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

    public static class KitViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public CheckBox checkBox;
        public EditText editText;
        public KitViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewItemName);
            checkBox  = itemView.findViewById(R.id.check);
            editText = itemView.findViewById(R.id.etQty);
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_kit,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Toast.makeText(getActivity(), barcode.displayValue, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "No QR Code captured", Toast.LENGTH_SHORT).show();
                }
            } else {

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_registro:
                Log.d(TAG, "registro");
                Intent intent = new Intent(getActivity(), GenerateQR.class);
                startActivity(intent);
            break;
            case R.id.action_kit:
                Intent intent2 = new Intent(getActivity(), BarcodeCaptureActivity.class);
                intent2.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                intent2.putExtra(BarcodeCaptureActivity.UseFlash, false);
                startActivityForResult(intent2, RC_BARCODE_CAPTURE);
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
