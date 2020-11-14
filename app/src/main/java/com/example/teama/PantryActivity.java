package com.example.teama;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

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

public class PantryActivity extends AppCompatActivity {
    private SearchView svIngredients; //search bar for user input
    private ListView myList, addedItemsList;
    private ArrayList<String> list; //arrayList that gets added
    private static ArrayList<Pantry_List> itemsList;
    private static ArrayAdapter<String> adapter; //allows us to link each item in myList to each string in list
    private static PantryAdapter adapterItems;

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

         //Only displays list when user clicks on search bar
        buildSearchList();
        buildGroceryList();
        createIngredientDB(list);


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

    private void buildSearchList() {
        list = new ArrayList<>();
        myList = (ListView) findViewById(R.id.myList);
        svIngredients = (SearchView) findViewById(R.id.searchIngredients);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list); //adapter takes in list; database
        myList.setAdapter(adapter);
    }

    private void buildGroceryList() {
        itemsList = new ArrayList<>();
        addedItemsList = (ListView)findViewById(R.id.checkable_added_items);
        addedItemsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapterItems = new PantryAdapter(this, R.layout.adapter_view_layout, itemsList);
        addedItemsList.setAdapter(adapterItems);

        addedItemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addedItemsList.setItemChecked(i, true);
            }
        });
    }

    private InputStream readInTextFiles() {
        InputStream inputStream = null;
        try {
            inputStream = getResources().getAssets().open("SEARCH_INGREDIENTS.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    private void createIngredientDB(ArrayList<String> list) {
        InputStreamReader streamReader;
        BufferedReader input;
        String item = "";
        try {
            streamReader = new InputStreamReader(readInTextFiles());
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
                            startActivity(new Intent(PantryActivity.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(PantryActivity.this, BoardActivity.class));
                            break;
                        case R.id.nav_Browser:
                            startActivity(new Intent(PantryActivity.this, BrowserActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            break;
                        case R.id.nav_Profile:
                            startActivity(new Intent(PantryActivity.this, ProfileActivity.class));
                            break;

                    }
                    return true;
                }
            };
}