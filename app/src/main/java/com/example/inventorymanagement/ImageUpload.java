package com.example.inventorymanagement;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;

public class ImageUpload extends AppCompatActivity {
ImageView img;
Button browse, upload;
Uri filepath;
Bitmap bitmap;
ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        img=(ImageView) findViewById(R.id.uploaded_image);
        browse=(Button) findViewById(R.id.browse);
        upload=(Button) findViewById(R.id.upload);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {



            public void onActivityResult(ActivityResult result) {
             if(result.getResultCode()==RESULT_OK){
           filepath=result.getData().getData();
                 Toast.makeText(ImageUpload.this, "Path copied", Toast.LENGTH_SHORT).show();
           try{
               InputStream inputStream= getContentResolver().openInputStream(filepath);
               bitmap = BitmapFactory.decodeStream(inputStream);
               img.setImageBitmap(bitmap);
           }
           catch (Exception e){
               Toast.makeText(ImageUpload.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
           }
       }
        else{
                 Toast.makeText(ImageUpload.this, "Something went wrong", Toast.LENGTH_SHORT).show();
             }

                }

        });
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(ImageUpload.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
//                                startActivityForResult(Intent.createChooser(intent, "Please select an image"),1);
                                activityResultLauncher.launch(intent);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();


            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadtofirebase();
            }
        });

    }

    private void uploadtofirebase() {
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle("File uploader");
        dialog.show();
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
        StorageReference uploader= firebaseStorage.getReference().child("image1");
        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();
                        Toast.makeText(ImageUpload.this, "File uploaded", Toast.LENGTH_LONG).show();

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot snapshot) {
                        float percent=(100* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();

                        dialog.setMessage("Uploaded :"+(int)percent+" %");
                    }
                });
    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//       if(resultCode==1 && resultCode==RESULT_OK){
//           filepath=data.getData();
//           try{
//               InputStream inputStream= getContentResolver().openInputStream(filepath);
//               bitmap = BitmapFactory.decodeStream(inputStream);
//               img.setImageBitmap(bitmap);
//           }
//           catch (Exception e){
//               Toast.makeText(this, "Failed with error "+e.getMessage(), Toast.LENGTH_SHORT).show();
//           }
//       }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

}