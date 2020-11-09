package com.example.teama;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private final List<Integer> mImages = new ArrayList<>(Arrays.asList(R.drawable.chicken_1, R.drawable.keto_1, R.drawable.pescatarian_1, R.drawable.veg_1, R.drawable.gluten_1 ));
    private final String[] file_names = {"chicken", "keto", "pescatarian", "vegetarian", "glutenfree"};
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter adapter;
    private ArrayList<String> mList;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(MainActivity.this, MealPrepActivity.class));
                            break;
                        case R.id.nav_Browser:
                            startActivity(new Intent(MainActivity.this, BrowserActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(MainActivity.this, MyPantry.class));
                            break;
                        case R.id.nav_Profile:
                            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                            break;

                    }
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        buildRecyclerView();

    }

    private void initializeValues() {
        mRecyclerView = findViewById(R.id.recycle_images);
        mLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter(mImages);
    }

    private void buildRecyclerView() {
        initializeValues();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        /* */
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mImages.get(position);
                createIngredientDB(mList, file_names[position]);
                String result = mList.get((int)(Math.random() * mList.size()));
                Intent resultIntent = new Intent(MainActivity.this, BrowserActivity.class);
                resultIntent.putExtra("result", result);
                resultIntent.putExtra("urlList", mList);
                startActivity(resultIntent);
                finish();
            }
        });
    }

    protected InputStream readInTextFiles(String str) {
        str += "_meals.txt";
        InputStream inputStream = null;
        try {
            inputStream = getResources().getAssets().open(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    protected void createIngredientDB(ArrayList<String> list, String str) {
        mList = new ArrayList<>();
        InputStreamReader streamReader;
        BufferedReader input;
        String item = "";
        try {
            streamReader = new InputStreamReader(readInTextFiles(str));
            input = new BufferedReader(streamReader);
            while ((item = input.readLine()) != null) {
                mList.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}