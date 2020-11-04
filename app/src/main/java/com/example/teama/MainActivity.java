package com.example.teama;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private int[] images = {R.drawable.chicken_1, R.drawable.keto_1, R.drawable.pescatarian_1, R.drawable.veg_1 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        recyclerView = findViewById(R.id.recycle_images);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(MainActivity.this, MealPrepActivity.class));
                            break;
                        case R.id.nav_Browser:
                            startActivity(new Intent(MainActivity.this, BrowserActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(MainActivity.this, MyPantry.class));
                            break;
                        case R.id.nav_Profile:
                            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                            break;

                    }
                    return true;
                }
            };
}