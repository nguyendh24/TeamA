package com.example.teama;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
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
    private Button addBtn;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        defaultSetting();

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            receiveIntent();
            buildWebView();
        } else {
            defaultWebView();
        }
    }

    public void defaultSetting() {
        bNext = findViewById(R.id.button_next);
        addBtn = findViewById(R.id.button_pin);
        webView = (WebView) findViewById(R.id.webView);
        bNext.setVisibility(View.GONE);
        addBtn.setVisibility(View.GONE);
    }

    public void receiveIntent() {
        Intent receiveIntent = getIntent(); //used to get result intent from MainActivity
        webURL = receiveIntent.getExtras().getString("result");
        position = receiveIntent.getExtras().getInt("index");
        urlList = receiveIntent.getStringArrayListExtra("urlList");
    }

    public void setButtonClick() {
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = (int)(Math.random() * urlList.size());
                webURL = urlList.get(position);
                webView.loadUrl("" + webURL);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(BrowserActivity.this, BoardActivity.class);
                resultIntent.putExtra("URL", webURL);
                resultIntent.putExtra("INDEX", position);
                startActivity(resultIntent);
                finish();
            }
        });
    }

    public void defaultWebView() {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.com");

        WebSettings webSet = webView.getSettings();
        webSet.setJavaScriptEnabled(true);
    }


    public void buildWebView() {
        bNext.setVisibility(View.VISIBLE);
        addBtn.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSet = webView.getSettings();
        webSet.setJavaScriptEnabled(true);

        webView.loadUrl("" + webURL);

        setButtonClick();
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
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            startActivity(new Intent(BrowserActivity.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(BrowserActivity.this, BoardActivity.class));
                            break;
                        case R.id.nav_Browser:
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(BrowserActivity.this, PantryActivity.class));
                            break;
                        case R.id.nav_Profile:
                            startActivity(new Intent(BrowserActivity.this, ProfileActivity.class));
                            break;
                    }
                    return true;
                }
            };
}