package com.example.teama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileActivity extends AppCompatActivity {
    private Button logoutButton;
    private TextView profileName;
    private TextView displayEmail;
    private TextView memberSince;
    private TextView dateOfBirth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final DrawerLayout DRAWER_LAYOUT = findViewById(R.id.Constraint);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        logoutButton = findViewById(R.id.signOut);
        profileName = findViewById(R.id.User_Name);
        displayEmail = findViewById(R.id.Login_Email);
        dateOfBirth = findViewById(R.id.Member_Date);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();
        String name = "";
        String email;
        Uri photoUri;
        //grabbing users information
        if (user != null){
            name = user.getDisplayName();
             email = user.getEmail();
             photoUri = user.getPhotoUrl();
            boolean emailVerified = user.isEmailVerified();
            profileName.setText(name);
            displayEmail.setText(email);

        }

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DRAWER_LAYOUT.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = findViewById(R.id.drawer_nav);
        navigationView.setItemIconTintList(null);
        logoutButton.setOnClickListener(new Button.OnClickListener() {
            //logout button
            public void onClick(View v){
                FirebaseAuth.getInstance().signOut(); //logs out the user
                startActivity(new Intent(getApplicationContext(), LoginActivity.class)); //sends to login activity
                finish();
            }
        });
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
                            startActivity(new Intent(ProfileActivity.this, BoardActivity.class));
                            break;
                        case R.id.nav_Browser:
                            startActivity(new Intent(ProfileActivity.this, BrowserActivity.class));
                            break;
                        case R.id.nav_Pantry:
                            startActivity(new Intent(ProfileActivity.this, PantryActivity.class));
                            break;
                        case R.id.nav_Profile:
                            break;
                    }
                    return true;
                }
            };
}