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
    private MainAdapter adapter;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        buildRecyclerView();

    }

    private void initializeValues() {
        mRecyclerView = findViewById(R.id.recycle_img_main);
        mLayoutManager = new LinearLayoutManager(this);
        adapter = new MainAdapter(mImages);
    }

    private void buildRecyclerView() {
        initializeValues();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        /* */
        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mImages.get(position);
                createIngredientDB(mList, file_names[position]);
                int indexResult = (int)(Math.random() * mList.size());
                String result = mList.get(indexResult);
                Intent sendIntent = new Intent(MainActivity.this, BrowserActivity.class);
                sendIntent.putExtra("result", result);
                sendIntent.putStringArrayListExtra("urlList", mList);
                sendIntent.putExtra("index", indexResult);
                startActivity(sendIntent);
                finish();
            }
        });
    }

    protected InputStream readInTextFiles(String str) {
        str += "_meals copy.txt";
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

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(MainActivity.this, BoardActivity.class));
                            break;
                        case R.id.nav_Browser:
                            startActivity(new Intent(MainActivity.this, BrowserActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(MainActivity.this, PantryActivity.class));
                            break;
                        case R.id.nav_Profile:
                            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                            break;

                    }
                    return true;
                }
            };
}