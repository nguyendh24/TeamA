package com.example.teama;
/**
 * Changing this button from settings to dietary restrictions that user
 * can specify and will populate appropriate recipes accordingly ?
 */

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SettingClass extends AppCompatActivity {
    private SearchView svDiets;
    private ListView myDietList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        svDiets = (SearchView) findViewById(R.id.searchView);
        myDietList = (ListView) findViewById(R.id.);
        list = new ArrayList<>();

        list.add("Keto");
        list.add("Low-carb");
        list.add("Paleo");
        list.add("Vegan");
        list.add("Atkins");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        myDietList.setAdapter(adapter);

        svDiets.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
}