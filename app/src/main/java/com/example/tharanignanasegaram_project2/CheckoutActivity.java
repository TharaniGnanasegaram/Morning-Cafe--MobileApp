package com.example.tharanignanasegaram_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CheckoutActivity extends AppCompatActivity {

    Button btnPaymentProceed;
    EditText name, street, unit, city, province, zip, country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        name = findViewById(R.id.txtinputname);
        street = findViewById(R.id.txtinputstreet);
        unit = findViewById(R.id.txtinputunit);
        city = findViewById(R.id.txtinputcity);
        province = findViewById(R.id.txtinputprovince);
        zip = findViewById(R.id.txtinputzip);
        country = findViewById(R.id.txtinputcountry);


            Double total =  getIntent().getDoubleExtra("TOTAL", 0.0);
            String totalStr = total.toString();
            String payment = getResources().getString(R.string.Payment);

            btnPaymentProceed = findViewById(R.id.btnPaymentProceed);
            btnPaymentProceed.setText(payment + " $"+ totalStr);

            btnPaymentProceed.setOnClickListener(view -> {

                if(name.getText().toString().equals("") ||  street.getText().toString().equals("") || unit.getText().toString().equals("") || city.getText().toString().equals("")
                        || province.getText().toString().equals("") || zip.getText().toString().equals("") || country.getText().toString().equals("")){
                    showAlert(getResources().getString(R.string.filldetails));

                }
                else {
                    Intent myintent = new Intent(this, PaymentActivity.class);
                    myintent.putExtra("TOTAL", total);
                    startActivity(myintent);
                }

            });

        }

    private AlertDialog showAlert(String errorMsg){

        return new AlertDialog.Builder(CheckoutActivity.this)
                .setTitle(getResources().getString(R.string.warningMsg))
                .setMessage(errorMsg)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert).show();
    }


}