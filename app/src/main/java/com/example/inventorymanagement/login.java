package com.example.inventorymanagement;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class login extends AppCompatActivity{
    EditText email, password;
   Button login,signup;
//    SignInButton google_login;
//    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
//    BeginSignInRequest signInRequest;
    private static int RC_SIGN_IN=100;
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);
//        google_login=findViewById(R.id.google_login);
//        google_login.setSize(SignInButton.SIZE_STANDARD);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
//        mGoogleSignInClient=GoogleSignIn.getClient(this, gso);
//        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
//        google_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch(v.getId()){
//                    case R.id.google_login:
//                        signIn();
//                        break;
//                }
//            }
//
//            private void signIn() {
//                Intent signInIntent=mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, RC_SIGN_IN);
//            }
//        });
        login.setOnClickListener(view->{
            loginUser();
        });
        signup.setOnClickListener(view->{
            startActivity(new Intent(com.example.inventorymanagement.login.this, com.example.inventorymanagement.signup.class));
        });


    }

//    private void google_login() {
//
//        signInRequest = BeginSignInRequest.builder()
//                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                        .setSupported(true)
//                        // Your server's client ID, not your Android client ID.
//                        .setServerClientId(getString(R.string.default_web_client_id))
//                        // Only show accounts previously used to sign in.
//                        .setFilterByAuthorizedAccounts(true)
//                        .build())
//                .build();
//
//        Toast.makeText(this, "signInRequest.", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    private void loginUser(){
        String email1=email.getText().toString();
        String password1=password.getText().toString();

        Toast.makeText(this, "email is "+email1 +" password is "+ password1, Toast.LENGTH_LONG).show();
        if(TextUtils.isEmpty(email1)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }
        else if(TextUtils.isEmpty(password1)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }
        else{
            mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(login.this, "User logged in successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, textCamCapture.class));

                    }
                    else{
                        Toast.makeText(login.this, "Something went wrong" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.w(  TAG, "something went wrong "+ task.getException().getMessage());
                    }
                }
            });
        }
    }


  
}
    
