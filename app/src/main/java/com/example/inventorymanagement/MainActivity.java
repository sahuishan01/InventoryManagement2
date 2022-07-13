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
    Button logout, scan, imageUpload, addContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout=findViewById(R.id.signout);
        info=findViewById(R.id.logged_in_info);
        scan=findViewById(R.id.scan);
        imageUpload=findViewById(R.id.imageUpload);
        addContent=findViewById(R.id.addContent);

        mAuth=FirebaseAuth.getInstance();
        addContent.setOnClickListener(view->{
            startActivity(new Intent(MainActivity.this, Inventory.class));
        });
        logout.setOnClickListener(view->{
            mAuth.signOut();
            startActivity(new Intent(com.example.inventorymanagement.MainActivity.this, com.example.inventorymanagement.login.class));

        });
        scan.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, Chemical_list.class));
        });
        imageUpload.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, ImageUpload.class));
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
            imageUpload.setVisibility(View.VISIBLE);
            addContent.setVisibility(View.VISIBLE);
            FirebaseUser user=mAuth.getCurrentUser();

            info.setText("Name of the user is "+ user.getDisplayName());
        }
    }
}