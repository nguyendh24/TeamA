package com.example.teama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.WidgetContainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class mealprep extends AppCompatActivity {
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
                        case R.id.nav_home:
                            startActivity(new Intent(mealprep.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            break;
                        case R.id.nav_Ingredients:
                            startActivity(new Intent(mealprep.this, IngredientsActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(mealprep.this, MyPantry.class));
                            break;
                        case R.id.nav_Settings:
                            startActivity(new Intent(mealprep.this, SettingClass.class));
                            break;

                    }
                    return true;
                }
            };
}