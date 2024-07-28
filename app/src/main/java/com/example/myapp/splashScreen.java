package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.dbControls.propertyControl;
import com.example.myapp.dbControls.userControl;

public class splashScreen extends AppCompatActivity {
    propertyControl dbPropertyControl;
    userControl dbUserControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        dbPropertyControl = new propertyControl(this);
        dbUserControl = new userControl(this);

        dbPropertyControl.createTable();
        dbUserControl.createTable();

        // Check if the user is logged in
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.contains("userId");
        Context c = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in;
                if (isLoggedIn)
                    in = new Intent(splashScreen.this, NavContent.class);
                else
                    in = new Intent(splashScreen.this, signup.class);

                in.putExtra("fragment", "home");
                startActivity(in);
                finish();
            }
        }, 4000);
    }
}