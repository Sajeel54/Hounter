package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.dbControls.propertyControl;
import com.example.myapp.models.property;

public class marketDetails extends AppCompatActivity {
    private propertyControl dbControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_details);
        dbControl = new propertyControl(this);

        // Assuming you pass the property object to this activity
        String searchId = getIntent().getStringExtra("search_id");

        // Initialize views
        ImageView imageView = findViewById(R.id.property_image);
        TextView priceTextView = findViewById(R.id.property_price);
        TextView descTextView = findViewById(R.id.property_description);
        TextView addressTextView = findViewById(R.id.property_address);
        TextView roomsTextView = findViewById(R.id.property_rooms);
        property value = dbControl.getProperty(searchId);


        // Set data to views
        imageView.setImageResource(value.getImage_res());
        priceTextView.setText(value.getPrice());
        descTextView.setText(value.getDesc());
        addressTextView.setText(value.getAddress());
        roomsTextView.setText(value.getRooms());

        Button btnBack = findViewById(R.id.redirect_to_marketplace_from_registryForm);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(marketDetails.this, marketplace.class);
                i.putExtra("fragment", "marketplace");
                startActivity(i);
            }
        });
    }
}