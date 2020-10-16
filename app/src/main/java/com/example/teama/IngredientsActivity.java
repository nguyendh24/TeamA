package com.example.teama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

public class IngredientsActivity extends AppCompatActivity {
    private SearchView svIngredients;

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);


        /**********************************************************
         * DELETE
         */
        name = findViewById(R.id.textView5);
        final Button nameButton = findViewById(R.id.button3);
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("Diana Nguyen");
            }
        });
        /**
         * DELETE
         **********************************************************/



    }
}