package com.example.teama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mealprep extends AppCompatActivity {

    //needs to get api call for google calendar or calendar that allows adding events etc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealprep);

        Button bckToRecipes = (Button)findViewById(R.id.bcktoRecipes);
        bckToRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mealprep.this,MainActivity.class));
            }
        });
    }
}