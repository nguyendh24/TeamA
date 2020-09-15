package com.example.teama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

        //setting button goes to setting activity
        final Button settings = (Button)findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SettingClass.class));
            }

        });
        //pantry button set to action
        Button pantrybtn = (Button)findViewById(R.id.pantryButton);
        pantrybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MyPantry.class));
            }
        });
    }
}