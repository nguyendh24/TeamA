package com.example.teama;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    EditText eRegEmail, eCreatePassword, eCheckPassword, eName;
    Button eSignUp;
    TextView mLoginBtn;
    FirebaseAuth fAuth;

    protected void onCreate(Bundle savedInstanceData){
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_registration);

        eName = findViewById(R.id.editTextTextPersonName);
        eRegEmail = findViewById(R.id.editTextRegEmail);
        eCreatePassword = findViewById(R.id.editTextRegPW);
        eCheckPassword = findViewById(R.id.editTextRegPW2);
        eSignUp = findViewById(R.id.buttonSignUp);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

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
                    }else {
                        fAuth.createUserWithEmailAndPassword(regEmail,regPW).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegistrationActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }else{
                                    Toast.makeText(RegistrationActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                }
            }
        });
    }
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}