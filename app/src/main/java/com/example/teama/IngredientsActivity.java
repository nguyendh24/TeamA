package com.example.teama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.File;
import java.util.ArrayList;

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
}