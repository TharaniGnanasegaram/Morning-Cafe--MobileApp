package com.example.tharanignanasegaram_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class PaymentActivity extends AppCompatActivity {

    Button btnPayment;
    EditText cardname, cardnum, expiry, ccv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardname = findViewById(R.id.txtCardName);
        cardnum = findViewById(R.id.txtCardNumber);
        expiry = findViewById(R.id.txtCardExp);
        ccv = findViewById(R.id.txtCardCcv);


        Double total =  getIntent().getDoubleExtra("TOTAL", 0.0);
        String totalStr = total.toString();
        String payment = getResources().getString(R.string.Payment);

        btnPayment = findViewById(R.id.btnPayment);
        btnPayment.setText(payment + " $"+ totalStr);

        btnPayment.setOnClickListener(view -> {

            if(cardnum.getText().toString().equals("") || cardname.getText().toString().equals("")|| expiry.getText().toString().equals("") || ccv.getText().toString().equals("")){
                showAlert(getResources().getString(R.string.emptyFieldValidation));
            }
            else if( !isNumeric(cardnum.getText().toString()) || cardnum.getText().length() != 16){
                showAlert(getResources().getString(R.string.cardNumberValidation));
            }
            else if( !isNumeric(ccv.getText().toString()) || ccv.getText().length() != 3){
                showAlert(getResources().getString(R.string.ccvValidation));
            }
            else {

                new AlertDialog.Builder(PaymentActivity.this)
                        .setTitle(getResources().getString(R.string.paymentConfirmation))
                        .setMessage(getResources().getString(R.string.paymentConfirm))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                new AlertDialog.Builder(PaymentActivity.this)
                                        .setTitle(getResources().getString(R.string.successMsg))
                                        .setMessage(getResources().getString(R.string.orderSuccess))
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent homeIntent = new Intent(PaymentActivity.this, ProductActivity.class);
                                                startActivity(homeIntent);
                                            }
                                        }).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert).show();

                Toast.makeText(this, getApplicationContext().getString(R.string.orderplaced) , Toast.LENGTH_SHORT);
            }

        });

    }

    private AlertDialog showAlert(String errorMsg){

        return new AlertDialog.Builder(PaymentActivity.this)
                .setTitle(getResources().getString(R.string.warningMsg))
                .setMessage(errorMsg)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    public static boolean isNumeric(String strPrice) {
        try {
            Double.parseDouble(strPrice);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}