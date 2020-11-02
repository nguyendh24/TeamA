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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    ArrayList<Pantry_List> itemsList;
    PantryAdapter adapterItems;



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


        svIngredients = (SearchView) findViewById(R.id.searchIngredients);
        myList = (ListView) findViewById(R.id.myList);
        addedItemsList = (ListView)findViewById(R.id.addedItems);
        list = new ArrayList<>();
        itemsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list); //adapter takes in list; database
        adapterItems = new PantryAdapter(this, R.layout.adapter_view_layout, itemsList);

        myList.setVisibility(View.GONE); //Only displays list when user clicks on search bar
        createIngredientDB(list, readInTextFiles());
        myList.setAdapter(adapter);
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
                Pantry_List pantry_item = new Pantry_List(selectedItem.toString(),1);
                itemsList.add(pantry_item);
            }
        });

    }

    protected String randomMethodBecauseICantFigureOutUnitTestForOtherOne(String value) {
        String str = "hello";

        return str += " " + value;
    }

    protected InputStream readInTextFiles() {
        InputStream inputStream = null;
        try {
            inputStream = getResources().getAssets().open("meats.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    protected void createIngredientDB(ArrayList<String> list, InputStream inputStream) {
        InputStreamReader streamReader;
        BufferedReader input;
        String item = "";
        try {
            streamReader = new InputStreamReader(inputStream);
            input = new BufferedReader(streamReader);
            while ((item = input.readLine()) != null) {
                list.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
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