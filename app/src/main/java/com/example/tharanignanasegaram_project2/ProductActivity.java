package com.example.tharanignanasegaram_project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MenuAdapter menuAdapter;
    CardView cardView;
    Button btnViewCard, btnAddProdView;
    List<Menu> productMenus;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("info", "Startingg");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        recyclerView = findViewById(R.id.recyclerViewProduct);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        productMenus = new ArrayList<Menu>();
//        menuAdapter = new MenuAdapter(ProductActivity.this, productMenus);

        databaseReference = FirebaseDatabase.getInstance().getReference("Menus");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Menu menu = dataSnapshot.getValue(Menu.class);
                    productMenus.add(menu);
                }

                menuAdapter = new MenuAdapter(ProductActivity.this, productMenus);
                recyclerView.setAdapter(menuAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnViewCard = findViewById(R.id.btnViewCard);

        btnViewCard.setOnClickListener(view -> {

            Intent orderIntent = new Intent(this, OrderActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("CARDMENUS",(Serializable)menuAdapter.cartItems);
            orderIntent.putExtra("BUNDLE",args);
            startActivity(orderIntent);
        });

        btnAddProdView = findViewById(R.id.btnAddProdView);
        btnAddProdView.setOnClickListener(view -> {
            Intent addProducrsIntent = new Intent(this, AddProducts.class);
            startActivity(addProducrsIntent);
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.i("info", "Resumingg");
//    }
}