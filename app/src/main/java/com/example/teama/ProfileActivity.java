package com.example.teama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final DrawerLayout drawerLayout = findViewById(R.id.Constraint);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = findViewById(R.id.drawer_nav);
        navigationView.setItemIconTintList(null);
    }
    //here to sign out user. Not sure if this is working correctly or not
    public void logout (View view){
        FirebaseAuth.getInstance().signOut(); //logs out the user
        startActivity(new Intent(getApplicationContext(), LoginActivity.class)); //sends to login activity
        finish();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_Main:
                            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                            break;
                        case R.id.nav_MealPrep:
                            startActivity(new Intent(ProfileActivity.this, MealPrepActivity.class));
                            break;
                        case R.id.nav_Browser:
                            startActivity(new Intent(ProfileActivity.this, BrowserActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(ProfileActivity.this, MyPantry.class));
                            break;
                        case R.id.nav_Profile:
                            break;

                    }
                    return true;
                }
            };
}