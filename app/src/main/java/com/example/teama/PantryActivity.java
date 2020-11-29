package com.example.teama;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.Arrays;

//needs a database to store peoples ingredients and recipes

public class PantryActivity extends AppCompatActivity {
    private SearchView svIngredients; //search bar for user input
    private ListView myList, addedItemsList;
    private ArrayList<String> list; //arrayList that gets added
    private static ArrayList<Pantry_List> itemsList;
    ArrayAdapter<String> adapter; //allows us to link each item in myList to each string in list
    private static PantryAdapter adapterItems;

    ArrayList<String> nameList = new ArrayList<>(Arrays.asList("milk", "yogurt", "cream cheese", "custard" , "butter", "cream cheese",
            "cottage cheese", "tzatziki", "buttermilk", "chicken", "ground chicken","beef", "ground beef",
            "ham", "pork", "ground pork", "turkey", "ground turkey", "bacon", "sausage", "lamb","potato", "carrot", "cabbage", "asparagus", "celery", "lettuce", "cabbage", "brussels sprouts",
            "spinach", "okra", "turnip", "onion", "ginger", "cucumber", "garlic", "cauliflower", "fennel", "pea", "eggplant" ,"shallot"));
    ArrayList<String> list2 = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pantry);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        svIngredients = findViewById(R.id.searchIngredients);
        myList = findViewById(R.id.checkable_added_items);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nameList);
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clicked = nameList.get(position);
                list2.add(clicked);
                Toast.makeText(PantryActivity.this, "You added "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });

        svIngredients.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                PantryActivity.this.adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                PantryActivity.this.adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
