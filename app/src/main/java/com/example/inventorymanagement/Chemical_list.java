package com.example.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Chemical_list extends AppCompatActivity {
    RecyclerView rclview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemical_list);

        rclview=(RecyclerView) findViewById(R.id.rclview);
        rclview.setLayoutManager(new LinearLayoutManager(this));

    }
}