package com.example.tharanignanasegaram_project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    TextView txtTotal;
    Double total = 0.0;

    Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        List<MenuCart> cardMenus = (List<MenuCart>) args.getSerializable("CARDMENUS");

        recyclerView = findViewById(R.id.orderRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        orderAdapter = new OrderAdapter(this, cardMenus);

        recyclerView.setAdapter(orderAdapter);
        btnCheckout = findViewById(R.id.checkOutBtn);

        txtTotal = findViewById(R.id.txtTotal);

            for(MenuCart menu : cardMenus){
                total += (menu.getPrice()*menu.getCountItem());
            }

            if(cardMenus.isEmpty()){
                btnCheckout.setEnabled(false);
            }else {
                total = Math.round(total * 100.0) / 100.0;
                txtTotal.setText("$"+total.toString());


                btnCheckout.setOnClickListener(view -> {
                    Intent checkoutIntent = new Intent(this, CheckoutActivity.class);
                    checkoutIntent.putExtra("TOTAL", total);
                    startActivity(checkoutIntent);
                });
            }


    }
}