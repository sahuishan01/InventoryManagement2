package com.example.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TextGraphic extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private Button scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_graphic);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        textView=findViewById(R.id.display_text);
        imageView=findViewById(R.id.scanned_img);
        scan=findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonReadText(v);
            }
        });

    }

    public void buttonReadText(View view){
        try {
            String stringFileName=null;
        }
        catch (Exception e){
            textView.setText("failed");
        }

    }
}