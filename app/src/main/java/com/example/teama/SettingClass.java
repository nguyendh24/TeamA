package com.example.teama;
/**
 * Changing this button from settings to dietary restrictions that user
 * can specify and will populate appropriate recipes accordingly ?
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SettingClass extends AppCompatActivity {
    private SearchView svDiets;
    private ListView myDietList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

         svDiets = (SearchView) findViewById(R.id.searchDiets);
         myDietList = (ListView) findViewById(R.id.listDiets);
         list = new ArrayList<>();

         list.add("Keto");
         list.add("Low-carb");
         list.add("Paleo");
         list.add("Vegan");
         list.add("Atkins");

         myDietList.setVisibility(View.GONE);
         adapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, list);
         myDietList.setAdapter(adapter);


        svDiets.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                if (!TextUtils.isEmpty(s)) {
                    myDietList.setVisibility(View.VISIBLE);
                } else {
                    myDietList.setVisibility(View.GONE);
                }
                return false;
            }
        });






    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            startActivity(new Intent(SettingClass.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(SettingClass.this, MealPrepActivity.class));
                            break;
                        case R.id.nav_Ingredients:
                            startActivity(new Intent(SettingClass.this, IngredientsActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(SettingClass.this, MyPantry.class));
                            break;
                        case R.id.nav_Profile:
                            break;

                    }
                    return true;
                }
            };

}