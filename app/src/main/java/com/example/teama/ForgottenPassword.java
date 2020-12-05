package com.example.teama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgottenPassword extends AppCompatActivity {
    private EditText resetEmail;
    private Button reset;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);
        resetEmail = findViewById(R.id.editTextEmailAddress);
        reset = findViewById(R.id.resetButton);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = resetEmail.getText().toString();
                fAuth.sendPasswordResetEmail(email);
                Toast.makeText(ForgottenPassword.this, "Email sent",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ForgottenPassword.this, LoginActivity.class));
            }
        });

    }
}