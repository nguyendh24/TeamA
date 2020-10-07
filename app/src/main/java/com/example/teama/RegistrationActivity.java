package com.example.teama;

import android.content.Intent;
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
    private EditText eName;
    //creates and instance of LoginDatabase
    LoginDatabase loginDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //database creation
        loginDb = new LoginDatabase(this);

        eName = findViewById(R.id.editTextTextPersonName);
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
                    }else {
                        //calling on the addData method after all conditions are met.
                        addData();
                    }
                }
            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    //adding user data to the database and sending to login page
    public void addData(){
        eSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calls on insertData method from LoginDatabase class
           boolean isInsert = loginDb.insertData(eName.getText().toString(),eRegEmail.getText().toString(),eCreatePassword.getText().toString());
           if(isInsert = true){
               Toast.makeText(RegistrationActivity.this,"Account created",Toast.LENGTH_LONG).show();
               startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
           }else
               Toast.makeText(RegistrationActivity.this,"Data failed to add",Toast.LENGTH_LONG).show();
            }
        });
    }
}