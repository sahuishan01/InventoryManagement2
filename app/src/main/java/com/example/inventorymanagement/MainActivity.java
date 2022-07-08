package com.example.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView info;
    Button logout, scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout=findViewById(R.id.signout);
        info=findViewById(R.id.logged_in_info);
        scan=findViewById(R.id.scan);
//        Intent i=new Intent(MainActivity.this, signup.class);
//        startActivity(i);
        mAuth=FirebaseAuth.getInstance();
        logout.setOnClickListener(view->{
            mAuth.signOut();
            startActivity(new Intent(com.example.inventorymanagement.MainActivity.this, com.example.inventorymanagement.login.class));

        });
        scan.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, textCamCapture.class));
        });
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(com.example.inventorymanagement.MainActivity.this, com.example.inventorymanagement.login.class));
        }
        else{
            scan.setVisibility(View.VISIBLE);
            info.setVisibility(View.VISIBLE);
            FirebaseUser user=mAuth.getCurrentUser();
            String user1=mAuth.getCurrentUser().getUid();
            info.setText("Name of the user is "+ user.getDisplayName());
        }
    }
}