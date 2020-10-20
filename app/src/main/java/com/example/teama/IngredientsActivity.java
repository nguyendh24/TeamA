package com.example.teama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class IngredientsActivity extends AppCompatActivity {
    private SearchView svIngredients;
    private ListView myList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    File meats;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        svIngredients = (SearchView) findViewById(R.id.searchView);
        meats = new File("meats.txt");
        myList = (ListView) findViewById(R.id.myList);
        list = new ArrayList<>();
        /**
         Scanner input= null;
         try {
         input = new Scanner(meats);
         } catch (FileNotFoundException e) {
         e.printStackTrace();
         }


         int i = 0;
         while(input.hasNext()) {
         String line = input.nextLine();
         list.add(line);
         i++;
         }
         */

        list.add("meats");
        list.add("dairy");
        list.add("eggs");
        list.add("vegetables");
        list.add("fruit");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        myList.setAdapter(adapter);

        svIngredients.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);

                return false;
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            startActivity(new Intent(IngredientsActivity.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(IngredientsActivity.this, mealprep.class));
                            break;
                        case R.id.nav_Ingredients:
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(IngredientsActivity.this, MyPantry.class));
                            break;
                        case R.id.nav_Settings:
                            startActivity(new Intent(IngredientsActivity.this, SettingClass.class));
                            break;

                    }
                    return true;
                }
            };
}