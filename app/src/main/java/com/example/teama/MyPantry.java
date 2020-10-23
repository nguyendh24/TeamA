package com.example.teama;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;

//needs a database to store peoples ingredients and recipes

public class MyPantry extends AppCompatActivity {
    private SearchView svIngredients;
    private ListView myList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    File meats;

    private ListView addedItemsList;
    ArrayList<String> itemsList;
    ArrayAdapter<String> adapterItems;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pantry);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        svIngredients = (SearchView) findViewById(R.id.searchIngredients);
        meats = new File("meats.txt");
        myList = (ListView) findViewById(R.id.myList);
        list = new ArrayList<>();

        addedItemsList = (ListView)findViewById(R.id.addedItems);
        itemsList = new ArrayList<>();

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

        myList.setVisibility(View.GONE);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, list);
        myList.setAdapter(adapter);

        svIngredients.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                if (!TextUtils.isEmpty(s)) {
                    myList.setVisibility(View.VISIBLE);
                } else {
                    myList.setVisibility(View.GONE);
                }
                return false;
            }
        });



        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object selectedItem = myList.getItemAtPosition(i);
                itemsList.add(selectedItem.toString());
            }
        });

        adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, itemsList);
        addedItemsList.setAdapter(adapterItems);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            startActivity(new Intent(MyPantry.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(MyPantry.this, MealPrepActivity.class));
                            break;
                        case R.id.nav_Ingredients:
                            startActivity(new Intent(MyPantry.this, IngredientsActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            break;
                        case R.id.nav_Profile:
                            startActivity(new Intent(MyPantry.this, ProfileActivity.class));
                            break;

                    }
                    return true;
                }
            };
}