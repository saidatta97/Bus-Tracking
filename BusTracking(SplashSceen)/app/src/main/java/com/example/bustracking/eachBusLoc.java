package com.example.bustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class eachBusLoc extends AppCompatActivity {

    TextView busNo;

    @Override
    public void onBackPressed() {

        startActivity(new Intent(getApplicationContext(),showBus.class));
//        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        busNo=(TextView)findViewById(R.id.textView);
        String busnumber = getIntent().getExtras().getString("busNo");
        busNo.setText(busnumber);
    }
}