package com.example.tharanignanasegaram_project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddProducts extends AppCompatActivity {

    Button btnChooseImage, btnAddMenu;
    ImageView menuImage;
    EditText txtMenuName, txtMenuDescription, txtMenuPrice;
    Uri menuUri;

    DatabaseReference databaseReference;
    StorageReference storageReference;

    int SUCCESS_CODE_SELECT_IMAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        btnChooseImage = findViewById(R.id.btnAddImage);
        menuImage = findViewById(R.id.menuImageView);
        btnAddMenu = findViewById(R.id.btnAddMenu);

        btnChooseImage.setOnClickListener(view ->  {
            imageChooser();
        });

        txtMenuName = findViewById(R.id.prodnameinput);
        txtMenuDescription = findViewById(R.id.proddescinput);
        txtMenuPrice = findViewById(R.id.prodpriceinput);

        btnAddMenu.setOnClickListener(view -> {
            if(txtMenuName.getText().toString().equals("") || txtMenuDescription.getText().toString().equals("") || txtMenuPrice.getText().toString().equals("")){

                showAlert(getResources().getString(R.string.filldetails));
            }
            else if(!isNumeric(txtMenuPrice.getText().toString())){

                showAlert(getResources().getString(R.string.invalidPriceError));
            }
            else if(menuUri == null){
                showAlert(getResources().getString(R.string.noFileSelected));
            }
            else {
                insertMenu();
                Intent menuIntent = new Intent(this, ProductActivity.class);

                startActivity(menuIntent);
            }

        });

        storageReference = FirebaseStorage.getInstance().getReference("Menus");
       databaseReference = FirebaseDatabase.getInstance().getReference("Menus");
    }


    void imageChooser() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SUCCESS_CODE_SELECT_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == SUCCESS_CODE_SELECT_IMAGE) {
                menuUri = data.getData();
                if (null != menuUri) {
                    menuImage.setImageURI(menuUri);
                }
            }
        }
    }

    public void insertMenu(){
        txtMenuName = findViewById(R.id.prodnameinput);
        txtMenuDescription = findViewById(R.id.proddescinput);
        txtMenuPrice = findViewById(R.id.prodpriceinput);

        String menuname = txtMenuName.getText().toString();
        String menuDesc = txtMenuDescription.getText().toString();
        Double menuPrice = Double.parseDouble(txtMenuPrice.getText().toString()) ;

        if(menuUri != null){

           final StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(menuUri));
            fileRef.putFile(menuUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Menu newMenu = new Menu(menuname, menuDesc, menuPrice, uri.toString());
                            String menuId = databaseReference.push().getKey();
                            databaseReference.child(menuId).setValue(newMenu);
                        }
                    });

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            showAlert(getResources().getString(R.string.noFileSelected));
                        }
                    });

        }else {
            showAlert(getResources().getString(R.string.noFileSelected));
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public static boolean isNumeric(String strPrice) {
        try {
            Double.parseDouble(strPrice);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private AlertDialog showAlert(String errorMsg){

        return new AlertDialog.Builder(AddProducts.this)
                .setTitle(getResources().getString(R.string.warningMsg))
                .setMessage(errorMsg)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert).show();
    }

}