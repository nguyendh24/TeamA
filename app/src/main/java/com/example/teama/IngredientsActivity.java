package com.example.teama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

public class IngredientsActivity extends AppCompatActivity {
    private SearchView svIngredients;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        db = new DatabaseHelper(this);

        svIngredients = findViewById(R.id.searchView);
        svIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });



    }
}