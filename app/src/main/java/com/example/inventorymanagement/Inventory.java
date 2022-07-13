package com.example.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Inventory extends AppCompatActivity {
    EditText chemical_name, chemical_formula, netQuantity, noOfBottles, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
    }

    public void submit(View view) {
        chemical_name=(EditText) findViewById(R.id.chemical_name);
        chemical_formula=(EditText) findViewById(R.id.chemical_formula);
        netQuantity=(EditText) findViewById(R.id.net_quantity);
        noOfBottles=(EditText) findViewById(R.id.number_of_bottles);
        description=(EditText) findViewById(R.id.description);

        String name=chemical_name.getText().toString().trim();
        String formula=chemical_formula.getText().toString().trim();
        String desc=description.getText().toString().trim();
        int quantityML=Integer.parseInt(netQuantity.getText().toString());
        int bottleAmount=Integer.parseInt(noOfBottles.getText().toString());

        dataHolder obj=new dataHolder(formula, desc, quantityML, bottleAmount);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance("https://inventory-23650-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference node=firebaseDatabase.getReference("Chemicals");


        node.child(name).setValue(obj);

        chemical_name.setText("");
        chemical_formula.setText("");
        netQuantity.setText("");
        description.setText("");
        noOfBottles.setText("");

        Toast.makeText(getApplicationContext(), "Insertion successful", Toast.LENGTH_LONG).show();


    }
}