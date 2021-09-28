package com.example.wonderengpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wonderengpractical.dbhandler.DatabaseHandler;

public class AddNewShopActivity extends AppCompatActivity {
    EditText nameShop, detailsShop, addressShop;
    Button saveBtn;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_shop);
        initViews();
        setListners();
        db = new DatabaseHandler(this);
    }

    private void setListners() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shopName = nameShop.getText().toString().trim();
                String details = detailsShop.getText().toString().trim();
                String address = addressShop.getText().toString().trim();
                if (shopName == null || shopName.isEmpty() || details == null || details.isEmpty() || address == null || address.isEmpty()) {
                    Toast.makeText(AddNewShopActivity.this, "Uh Oh!" + "\n" + "Fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    address = "6.511815,81.197263";
                    address = "6.162401,80.669575";
                    address = "6.249776,80.220251";

                    db.addShop(shopName, details, address);
                    Intent i=new Intent(AddNewShopActivity.this,MapsActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }

    private void initViews() {
        nameShop = findViewById(R.id.nameShop);
        detailsShop = findViewById(R.id.detailsShop);
        addressShop = findViewById(R.id.addressShop);
        saveBtn = findViewById(R.id.saveBtn);
    }
}