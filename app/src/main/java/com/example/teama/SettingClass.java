package com.example.teama;
/**
 * Changing this button from settings to dietary restrictions that user
 * can specify and will populate appropriate recipes accordingly ?
 */

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        svDiets = (SearchView) findViewById(R.id.searchDiets);
        myDietList = (ListView) findViewById(R.id.listDiets);
        list = new ArrayList<>();

        list.add("Keto");
        list.add("Low-carb");
        list.add("Paleo");
        list.add("Vegan");
        list.add("Atkins");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        myDietList.setAdapter(adapter);

        /**
        myDietList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
           }
       });
         */


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