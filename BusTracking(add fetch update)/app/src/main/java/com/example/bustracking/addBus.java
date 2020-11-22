package com.example.bustracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class addBus extends AppCompatActivity {

//    DatabaseReference busDetails;
    TextInputLayout busNo,location,destination,startTime,reachTime;
    Button addBus;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        busNo= (TextInputLayout) findViewById(R.id.busNo);
        location=(TextInputLayout) findViewById(R.id.Location);
        destination= (TextInputLayout)findViewById(R.id.destination);
        startTime= (TextInputLayout)findViewById(R.id.startTime);
        reachTime= (TextInputLayout)findViewById(R.id.reachTime);
        addBus =(Button)findViewById(R.id.addBus);
//        busDetails = FirebaseDatabase.getInstance().getReference().child("busDetails");

//        addBus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("BusDetails");

//                String busNumber = busNo.getEditText().getText().toString();
//                String busLocation = location.getEditText().getText().toString();
//                String busDestination = destination.getEditText().getText().toString();
//                String busStartTime = startTime.getEditText().getText().toString();
//                String busReachTime = reachTime.getEditText().getText().toString();

//                UserHelperClass helperClass = new UserHelperClass(busNumber, busLocation, busDestination, busStartTime, busReachTime);
//                reference.child(busNumber).setValue(helperClass);




                //                HashMap<String,Object> map = new HashMap<>();
//                map.put("busNo",busNo.getEditText().getText().toString());
//                map.put("busLocation",location.getEditText().getText().toString());
//                map.put("busDestination",destination.getEditText().getText().toString());
//                map.put("busStartTime",startTime.getEditText().getText().toString());
//                map.put("busReachTime",reachTime.getEditText().getText().toString());
//
//                busDetails.push()
//                        .setValue(map)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Log.i("jfbvkj", "onComplete: ");
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.i("jfbvkj", "onFailure: "+e.toString());
//                            }
//                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.i("jfbvkj", "onSuccess: ");
//                    }
//                });
//

//          }});
        }

    public void Add(View view) {

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("BusDetails");

                String busNumber = busNo.getEditText().getText().toString();
                String busLocation = location.getEditText().getText().toString();
                String busDestination = destination.getEditText().getText().toString();
                String busStartTime = startTime.getEditText().getText().toString();
                String busReachTime = reachTime.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(busNumber, busLocation, busDestination, busStartTime, busReachTime);
                reference.child(busNumber).setValue(helperClass)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(addBus.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(addBus.this, "Data added", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(addBus.this,update.class);
                        startActivity(i);

                    }
                });



        // 2nd method using map function
//
//        reference = FirebaseDatabase.getInstance().getReference().child("BusDetails");
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("busNo",busNo.getEditText().getText().toString());
//        map.put("busLocation",location.getEditText().getText().toString());
//        map.put("busDestination",destination.getEditText().getText().toString());
//        map.put("busStartTime",startTime.getEditText().getText().toString());
//        map.put("busReachTime",reachTime.getEditText().getText().toString());
//
//        reference.push()
//                .setValue(map)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(addBus.this, "Data Added", Toast.LENGTH_SHORT).show();
//
//                        Intent i = new Intent(addBus.this,update.class);
//                        startActivity(i);
//                    }
//                });

    }
}
