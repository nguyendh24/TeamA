package com.example.teama;

/**
 * Want to create a login screen that requires a 6 character long password
 * where the button wont be active to click until req reached
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    /**
     * Defining all the elements of layout
     */

    private EditText eEmail;
    private EditText ePassword;
    private TextView eAttempts;
    private Button eLogin;
    private TextView eNewUser;

    private int counter = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmail = findViewById(R.id.editTextEmailAddress); //findViewById connects/binds to respective xml layout to variable
        ePassword = findViewById(R.id.editTextRegPW);
        eAttempts = findViewById(R.id.textViewAttempts);
        eLogin = findViewById(R.id.buttonLogin);
        eNewUser = findViewById(R.id.textViewSignUp);

        eNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));

                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));

            }
        });

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputEmail = eEmail.getText().toString(); //need toString to convert eEmail
                String inputPW = ePassword.getText().toString();
                admin(inputEmail, inputPW);

                if (inputEmail.isEmpty() || inputPW.isEmpty()) { //valid input
                    Toast.makeText(LoginActivity.this, "invalid input ",Toast.LENGTH_SHORT).show();
                } else {
                    if (!isValidEmail(inputEmail)) {
                        counter--;
                        eAttempts.setText("# of attempts remaining: " + counter);
                        Toast.makeText(LoginActivity.this, "invalid email *", Toast.LENGTH_SHORT).show();
                        if (counter == 0)
                            eLogin.setEnabled(false); //if user exceed login attempts button will be disabled
                    } else {
                        Toast.makeText(LoginActivity.this, "Login success! ",Toast.LENGTH_SHORT).show();
                        //sends user to the main activity page
                        Button loginbtn = (Button)findViewById(R.id.buttonLogin);
                        loginbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                        });

                    }
                }
            }
        });
    }

    /**
     * Validating appropriate Email -- using Patterns &TextUtil
     * @param target
     * @return
     */
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void admin(String admin, String adminPW) {
        if (admin.equalsIgnoreCase("admin") && adminPW.equalsIgnoreCase("123")) {
            setContentView(R.layout.activity_main);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

    }
}