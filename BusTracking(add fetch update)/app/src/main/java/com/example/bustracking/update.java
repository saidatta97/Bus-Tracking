package com.example.bustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class update extends AppCompatActivity {

    TextInputLayout busNo,location,destination,startTime,reachTime;
    Button update;
    TextView text;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        text=(TextView)findViewById(R.id.text);
        busNo= (TextInputLayout) findViewById(R.id.busNo);
        location=(TextInputLayout) findViewById(R.id.Location);
        destination= (TextInputLayout)findViewById(R.id.destination);
        startTime= (TextInputLayout)findViewById(R.id.startTime);
        reachTime= (TextInputLayout)findViewById(R.id.reachTime);
        update =(Button)findViewById(R.id.update);

        reference = FirebaseDatabase.getInstance().getReference("BusDetails");

        read();



    }
    public void update(View view) {

                HashMap<String,Object> map = new HashMap<>();
                map.put("busNo",busNo.getEditText().getText().toString());
                map.put("busLocation",location.getEditText().getText().toString());
                map.put("busDestination",destination.getEditText().getText().toString());
                map.put("busStartTime",startTime.getEditText().getText().toString());
                map.put("busReachTime",reachTime.getEditText().getText().toString());

                reference.child("111")
                .updateChildren(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(update.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    }
                });



    }
    public void read() {

        reference.child("111")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String post = "Title : "+dataSnapshot.child("title").getValue(String.class)+"\n"
//                                +"Description : "+dataSnapshot.child("description").getValue(String.class)+"\n"
//                                +"Author : "+dataSnapshot.child("author").getValue(String.class);

//                        text.setText(reference);
                        String busNo1=dataSnapshot.child("busNumber").getValue(String.class);
                        String location1=dataSnapshot.child("busLocation").getValue(String.class);
                        String destination1=dataSnapshot.child("busDestination").getValue(String.class);
                        String startTime1=dataSnapshot.child("busStartTime").getValue(String.class);
                        String reachTime1=dataSnapshot.child("busReachTime").getValue(String.class);

                        busNo.getEditText().setText(busNo1);
                        location.getEditText().setText(location1);
                        destination.getEditText().setText(destination1);
                        startTime.getEditText().setText(startTime1);
                        reachTime.getEditText().setText(reachTime1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
    }