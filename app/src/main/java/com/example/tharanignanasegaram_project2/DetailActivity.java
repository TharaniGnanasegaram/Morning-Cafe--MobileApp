package com.example.tharanignanasegaram_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       String title = getIntent().getStringExtra("TITLE");
       String image = getIntent().getStringExtra("IMAGE");
       String price = getIntent().getStringExtra("PRICE");
       String description = getIntent().getStringExtra("DESC");


        TextView menuName = findViewById(R.id.displayMenuName);
       TextView menuDescription = findViewById(R.id.displayMenuDescription);
       ImageView menuImage = findViewById(R.id.displayMenuImage);
       TextView menuPrice = findViewById(R.id.displayMenuPrice);


       menuName.setText(title);
       Picasso.with(getBaseContext()).load(image).into(menuImage);
       menuPrice.setText(price);
       menuDescription.setText(description);

//        Picasso.with(mcontext).load(currentMenu.getMenuImage()).into(holder.image);
    }
}