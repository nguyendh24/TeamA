package com.example.teama;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    /**
     * Data Fields
     */
    private EditText eRegEmail;
    private EditText eCreatePassword;
    private EditText eCheckPassword;
    private Button eSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegEmail = findViewById(R.id.editTextRegEmail);
        eCreatePassword = findViewById(R.id.editTextRegPW);
        eCheckPassword = findViewById(R.id.editTextRegPW2);
        eSignUp = findViewById(R.id.buttonSignUp);


        eSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regEmail = eRegEmail.getText().toString();
                String regPW = eCreatePassword.getText().toString();
                String pwCheck = eCheckPassword.getText().toString();

                if (regEmail.isEmpty() || regPW.isEmpty() || pwCheck.isEmpty()) { //valid input
                    Toast.makeText(RegistrationActivity.this, "invalid input", Toast.LENGTH_SHORT).show();
                } else {
                    if(!isValidEmail(regEmail)) {
                        Toast.makeText(RegistrationActivity.this, "invalid email", Toast.LENGTH_SHORT).show();
                    } else if (!regPW.equals(pwCheck)) {
                        Toast.makeText(RegistrationActivity.this, "passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    /****
     * Testing commit to branch 
     */
}