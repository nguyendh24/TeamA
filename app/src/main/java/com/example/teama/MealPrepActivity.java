package com.example.teama;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MealPrepActivity extends AppCompatActivity {
    private CalendarView calendar;



    //needs to get api call for google calendar or calendar that allows adding events etc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealprep);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        calendar = findViewById(R.id.calendarView);




    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            startActivity(new Intent(MealPrepActivity.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            break;
                        case R.id.nav_Browser:
                            startActivity(new Intent(MealPrepActivity.this, BrowserActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(MealPrepActivity.this, MyPantry.class));
                            break;
                        case R.id.nav_Profile:
                            startActivity(new Intent(MealPrepActivity.this, ProfileActivity.class));
                            break;

                    }
                    return true;
                }
            };
}