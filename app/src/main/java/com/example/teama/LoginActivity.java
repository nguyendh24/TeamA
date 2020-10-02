package com.example.teama;

/**
 * Want to create a login screen that requires a 6 character long password
 * where the button wont be active to click until req reached
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity  {

    /**
     * Defining all the elements of layout
     */

    private EditText eEmail;
    private EditText ePassword;
    private TextView eAttempts;
    private Button eLogin;
    private TextView eNewUser;
    private int counter = 5;

    SignInButton signIn;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmail = findViewById(R.id.editTextEmailAddress); //findViewById connects/binds to respective xml layout to variable
        ePassword = findViewById(R.id.editTextRegPW);
        eAttempts = findViewById(R.id.textViewAttempts);
        eLogin = findViewById(R.id.buttonLogin);
        eNewUser = findViewById(R.id.textViewSignUp);


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
                //admin(inputEmail, inputPW);

                if (inputEmail.equalsIgnoreCase("admin") && inputPW.equalsIgnoreCase("123")) {
                    setContentView(R.layout.activity_main);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else if (inputEmail.isEmpty() || inputPW.isEmpty()) { //valid input
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
                       // Button loginbtn = (Button)findViewById(R.id.buttonLogin);
                        eLogin.setOnClickListener(new View.OnClickListener() {
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

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account); **create intent to second activity!!
    }
}