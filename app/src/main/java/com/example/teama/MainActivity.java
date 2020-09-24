package com.example.teama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button bIngredients;
    private Button bSettings;
    private Button bPantry;
    private Button bMealPrep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bIngredients = findViewById(R.id.buttonIngredients);
        bSettings = findViewById(R.id.settings);
        bPantry = findViewById(R.id.pantryButton);
        bMealPrep = findViewById(R.id.buttonMealPrep);

        bIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, IngredientsActivity.class));
            }
        });
        bSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Setting button goes to setting activity
                startActivity(new Intent(MainActivity.this, SettingClass.class));
            }

        });

        bPantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Pantry button set to action
                startActivity(new Intent(MainActivity.this, MyPantry.class));
            }
        });

        bMealPrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // mealPrep button to go to calendar view
                startActivity(new Intent(MainActivity.this,mealprep.class));
            }
        });


    }
}