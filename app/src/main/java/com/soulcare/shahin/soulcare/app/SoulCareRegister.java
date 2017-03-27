package com.soulcare.shahin.soulcare.app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by shahin on 10-03-17.
 */

public class SoulCareRegister extends AppCompatActivity{
    SharedPreferences AlreadyReigister;
    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;


    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        //if Alredy Register or  Login then operation
        if (auth.getCurrentUser() != null) {

            startActivity(new Intent(SoulCareRegister.this, MainActivity.class));
            finish();

        }
//Start new
       else{
            Intent intent= new Intent(this,App_Intro.class);
            startActivity(intent);
           //Set the transition -> method available from Android 2.0 and beyond
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
        }

        setContentView(R.layout.account_register_user);
        //Get firebase Authintication

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        TextView textView = (TextView) this.findViewById(R.id.textview_marquee);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setText("General Information... general information... General Information");
        textView.setSelected(true);
        textView.setSingleLine(true);





        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SoulCareRegister.this,LoginActivityUser.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    //customtoast

                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast_layout_root));

                    ImageView image = (ImageView) layout.findViewById(R.id.image);
                    image.setImageResource(R.drawable.toast);
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText(" Please enter your valid gmail address !");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast_layout_root));

                    ImageView image = (ImageView) layout.findViewById(R.id.image);
                    image.setImageResource(R.drawable.toast);
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("Please enter a well known Password !");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();                    return;
                }

                if (password.length() < 6) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast_layout_root));

                    ImageView image = (ImageView) layout.findViewById(R.id.image);
                    image.setImageResource(R.drawable.toast);
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("Password too short, enter minimum 6 characters !");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SoulCareRegister.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                AlertDialog.Builder dialog = new AlertDialog.Builder( SoulCareRegister.this );
                                dialog.setTitle( "Authintication");
                                dialog.setMessage("createUserWithEmail:onComplete:" + task.isSuccessful());
                                dialog.setIcon(R.drawable.alert);

                                dialog.setPositiveButton( "Ok", null );
                                dialog.show();

                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {

                                    AlertDialog.Builder dialogtwo = new AlertDialog.Builder( SoulCareRegister.this );
                                    dialogtwo.setTitle( "Authentication failed.");
                                    dialogtwo.setMessage(""+task.getException());

                                    dialogtwo.setIcon(R.drawable.alert);

                                    dialogtwo.setPositiveButton( "Ok", null );
                                    dialogtwo.show();

                                }
                                else {
                                    startActivity(new Intent(SoulCareRegister.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });




    }




    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }



}


