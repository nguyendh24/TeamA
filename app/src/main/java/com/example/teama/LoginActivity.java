package com.example.teama;

/**
 * Want to create a login screen that requires a 6 character long password
 * where the button wont be active to click until req reached
 */

import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    /**
     * Defining all the elements of layout
     */
    LoginDatabase loginDb;
    private EditText eEmail;
    private EditText ePassword;
    private TextView eAttempts;
    private Button eLogin;
    private TextView eNewUser;
    private int counter = 5;
    private String TAG;
    private TextView forgotPassword;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    SignInButton signIn;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //instantiate
        eEmail = findViewById(R.id.editTextEmailAddress); //findViewById connects/binds to respective xml layout to variable
        ePassword = findViewById(R.id.editTextRegPW);
        eAttempts = findViewById(R.id.textViewAttempts);
        eLogin = findViewById(R.id.buttonLogin);
        eNewUser = findViewById(R.id.textViewSignUp);
        fAuth = FirebaseAuth.getInstance();
        forgotPassword = findViewById(R.id.ResetPassword);



        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        signIn = findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        forgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgottenPassword.class));
            }
        });
        eNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputEmail = eEmail.getText().toString(); //need toString to convert eEmail
                String inputPW = ePassword.getText().toString();

                //admin login
                if(inputEmail.equalsIgnoreCase("admin") && inputPW.equalsIgnoreCase("123")){
                    //setContentView(R.layout.activity_main);
                    Toast.makeText(LoginActivity.this,"Admin login",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                //if no email
                if(TextUtils.isEmpty(inputEmail)){
                    eEmail.setError("Email is required.");
                }
                //if no password
                if(TextUtils.isEmpty(inputPW)){
                    ePassword.setError("Password is required.");

                }
                //if password isn't long enough
                if(inputPW.length() < 6){
                    ePassword.setError("Password must be at least 6 characters.");
                }

                //authenticate the user
                fAuth.signInWithEmailAndPassword(inputEmail,inputPW).addOnCompleteListener(new OnCompleteListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login in complete", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this,"Error! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * Validating appropriate Email -- using Patterns &TextUtil
     *
     * @param target
     * @return
     */
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    /**
     * Google stuff
     */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    /**
     * The GoogleSignInAccount object contains information about the signed-in user, such as the user's name.
     */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            //Signed in success, show authenticated UI
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // updateUI(currentUser);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account); **create intent to second activity!!
    }
}



