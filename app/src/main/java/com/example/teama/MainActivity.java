package com.example.teama;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.bonappetit.com/recipe/double-garlic-roast-chicken-with-onion-gravy");

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
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
                            startActivity(new Intent(MainActivity.this, MealPrepActivity.class));
                            break;
                        case R.id.nav_Ingredients:
                            startActivity(new Intent(MainActivity.this, IngredientsActivity.class));
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
}