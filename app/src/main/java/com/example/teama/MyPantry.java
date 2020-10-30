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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//needs a database to store peoples ingredients and recipes

public class MyPantry extends AppCompatActivity {
    private SearchView svIngredients; //search bar for user input
    private ListView myList; //list view of ingredients from searching
    ArrayList<String> list; //arrayList that gets added
    ArrayAdapter<String> adapter; //allows us to link each item in myList to each string in list
    Scanner input; //Global Scanner for reading in different .txt files

    private ListView addedItemsList; //will be user's current "on hand groceries"
    ArrayList<String> itemsList;
    ArrayAdapter<String> adapterItems;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pantry);
        /**
         * BottomNav bar instantiated and sets the buttons from navListener
         */
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        /**
         * variables used for user to search thru ingredients database
         */
        svIngredients = (SearchView) findViewById(R.id.searchIngredients);
        myList = (ListView) findViewById(R.id.myList);
        list = new ArrayList<>();
        try {
            createIngredientDB(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * variables used for selected searched items
         */
        addedItemsList = (ListView)findViewById(R.id.addedItems);
        itemsList = new ArrayList<>();


        myList.setVisibility(View.GONE); //Only displays list when user clicks on search bar
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, list); //adapter takes in list; database
        myList.setAdapter(adapter);

        adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
        addedItemsList.setAdapter(adapterItems);

        svIngredients.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                svIngredients.onActionViewExpanded();
                if (!TextUtils.isEmpty(s)) {
                    myList.setVisibility(View.VISIBLE);
                    addedItemsList.setVisibility(View.GONE);
                } else {
                    myList.setVisibility(View.GONE);
                    addedItemsList.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });



        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object selectedItem = myList.getItemAtPosition(i);
                svIngredients.onActionViewCollapsed();
                itemsList.add(selectedItem.toString());
            }
        });

    }

    protected void createIngredientDB(ArrayList<String> list) throws FileNotFoundException {
        input = new Scanner(new File("meats.txt"));
        while(input.hasNext()) {
            String str = input.nextLine();
            list.add(str);
        }
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
                        case R.id.nav_Browser:
                            startActivity(new Intent(MyPantry.this, BrowserActivity.class));
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