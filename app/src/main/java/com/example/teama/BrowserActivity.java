package com.example.teama;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity {
    private WebView webView;
    private String webURL;
    private Button bNext;
    private ArrayList<String> urlList;

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            startActivity(new Intent(BrowserActivity.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(BrowserActivity.this, MealPrepActivity.class));
                            break;
                        case R.id.nav_Browser:
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(BrowserActivity.this, MyPantry.class));
                            break;
                        case R.id.nav_Profile:
                            startActivity(new Intent(BrowserActivity.this, ProfileActivity.class));
                            break;
                    }
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        bNext = findViewById(R.id.button_next);

        Intent intent = getIntent();
        webURL = intent.getExtras().getString("result");
        urlList = (ArrayList<String>) getIntent().getSerializableExtra("urlList");
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        if (webURL.equals("")) {
            webURL = "google.com";
        }
        webView.loadUrl("" + webURL);

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webURL = urlList.get((int)(Math.random() * urlList.size()));
                webView.loadUrl("" + webURL);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}