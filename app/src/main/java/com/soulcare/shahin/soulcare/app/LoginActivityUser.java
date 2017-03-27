package com.soulcare.shahin.soulcare.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.transition.TransitionManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.support.design.R.id.visible;

public class LoginActivityUser extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    private String  email = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivityUser.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login_user);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivityUser.this, SoulCareRegister.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivityUser.this);
                builder.setTitle("Enter Your Email");

// Set up the input
                final EditText input = new EditText(LoginActivityUser.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         email = input.getText().toString().trim();

                        if (TextUtils.isEmpty(email)) {
                            Toast.makeText(getApplication(), "Enter your registered email id"+email, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        progressBar.setVisibility(View.VISIBLE);
                        auth.sendPasswordResetEmail(email)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            AlertDialog.Builder dialog = new AlertDialog.Builder( LoginActivityUser.this );
                                            dialog.setTitle( "Please check email!");
                                            dialog.setMessage("We have sent you instructions to reset your password!");
                                            dialog.setIcon(R.drawable.hert);

                                            dialog.setPositiveButton( "Ok", null );
                                            dialog.show();
                                        } else {
                                            AlertDialog.Builder dialog = new AlertDialog.Builder( LoginActivityUser.this );

                                            dialog.setTitle( "Failed to send reset email!");
                                            dialog.setIcon(R.drawable.alert);

                                            dialog.setPositiveButton( "Ok", null );
                                            dialog.show();
                                        }

                                        progressBar.setVisibility(View.GONE);
                                    }
                                });

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivityUser.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        AlertDialog.Builder dialog = new AlertDialog.Builder( LoginActivityUser.this );
                                        dialog.setTitle( "Login");
                                        dialog.setMessage("" + task.isSuccessful());
                                        dialog.setMessage("Resons" + task.getException());
                                        dialog.setIcon(R.drawable.alert);

                                        dialog.setPositiveButton( "Ok", null );
                                        dialog.show();
                                    }
                                }
                                else {
                                    Intent intent = new Intent(LoginActivityUser.this, MainActivity.class);
                                    overridePendingTransition(R.anim.fab_scale_up,R.anim.fab_scale_up);

                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
