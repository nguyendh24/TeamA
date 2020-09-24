package com.example.teama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button bIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bIngredients = findViewById(R.id.buttonIngredients);
<<<<<<< HEAD
=======

        final Button mealBtn = findViewById(R.id.MealPrep);
        mealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,mealprep.class));
            }
        });
>>>>>>> b531e9f822fb62c9135d36908283f059b674c5de

        //setting button goes to setting activity
        final Button settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingClass.class));
            }

        });
        //pantry button set to action
        Button pantrybtn = (Button) findViewById(R.id.pantryButton);
        pantrybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyPantry.class));
            }
        });

<<<<<<< HEAD
=======
        //mealPrep button to go to calendar view

>>>>>>> b531e9f822fb62c9135d36908283f059b674c5de
    }
}