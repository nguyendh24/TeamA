package com.example.teama;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//needs a database to store peoples ingredients and recipes

public class MyPantry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pantry);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            startActivity(new Intent(MyPantry.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(MyPantry.this, MealPrepActivity.class));
                            break;
                        case R.id.nav_Ingredients:
                            startActivity(new Intent(MyPantry.this, IngredientsActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            break;
                        case R.id.nav_Profile:
                            startActivity(new Intent(MyPantry.this, ProfileActivity.class));
                            break;

                    }
                    return true;
                }
            };
}