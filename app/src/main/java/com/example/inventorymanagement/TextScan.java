package com.example.inventorymanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class TextScan extends AppCompatActivity {
    Button button_capture, button_copy;
    TextView textView_data;
    Bitmap bitmap;
    private static final int REQUEST_CAMERA_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_scan);

        button_capture=findViewById(R.id.button_capture);
        button_copy=findViewById(R.id.button_copy);
        textView_data=findViewById(R.id.text_data);

//        get camera permission
        if (ContextCompat.checkSelfPermission(TextScan.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(TextScan.this, new String[]{
                    Manifest.permission.CAMERA
            },REQUEST_CAMERA_CODE);
        }
        button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(TextScan.this);

            }
        });

        button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scannedText=textView_data.getText().toString();
                copyCode(scannedText);
            }
        });
    }

    private void copyCode(String text) {
        ClipboardManager clipboardManager=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData=ClipData.newPlainText("copied data", text);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(this, "Text has been copied", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if (resultCode==RESULT_OK){
                Uri resultUri= result.getUri();
                try {
                    bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    getTextFromImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void getTextFromImage(Bitmap bitmap){
        TextRecognizer recognizer=new TextRecognizer.Builder(this).build();
        if( !recognizer.isOperational()){
            Toast.makeText(TextScan.this, "Error occured", Toast.LENGTH_SHORT).show();
        }
        else{
            Frame frame=new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray=recognizer.detect(frame);
            StringBuilder stringBuilder=new StringBuilder();
            for (int i=0; i<textBlockSparseArray.size(); i++){
                TextBlock textBlock=textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");

            }
            textView_data.setText(stringBuilder.toString());
            button_capture.setText("Retake");
            button_copy.setVisibility(View.VISIBLE);
        }
    }
}