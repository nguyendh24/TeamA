package com.example.teama;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardActivity extends AppCompatActivity {
    private RecyclerView bRecyclerView;
    private RecyclerView.LayoutManager bLayoutManager;
    private BoardAdapter adapter;
    private final List<Integer> veg_img = new ArrayList<>(Arrays.asList(R.drawable.veg0, R.drawable.veg1, R.drawable.veg2, R.drawable.veg3, R.drawable.veg4));

    private static List<Board_Gallery> bImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        buildGallery();
    }

    private void initializeValues() {
        bRecyclerView = findViewById(R.id.recycle_recipe_board);
        bLayoutManager = new GridLayoutManager(this,2);
        adapter = new BoardAdapter(this, bImages);
    }

    private void buildGallery() {
        String URL = "";
        int index = 0;
        Bundle bundle = getIntent().getExtras();
        if(bundle !=null) {
            URL = bundle.getString("URL");
            index = bundle.getInt("INDEX");
        }


        bImages.add(new Board_Gallery(String.valueOf(index), URL, veg_img.get(index)));

        initializeValues();
        bRecyclerView.setHasFixedSize(true);
        bRecyclerView.setLayoutManager(bLayoutManager);
        bRecyclerView.setAdapter(adapter);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            startActivity(new Intent(BoardActivity.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            break;
                        case R.id.nav_Browser:
                            startActivity(new Intent(BoardActivity.this, BrowserActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(BoardActivity.this, PantryActivity.class));
                            break;
                        case R.id.nav_Profile:
                            startActivity(new Intent(BoardActivity.this, ProfileActivity.class));
                            break;
                    }
                    return true;
                }
            };
}