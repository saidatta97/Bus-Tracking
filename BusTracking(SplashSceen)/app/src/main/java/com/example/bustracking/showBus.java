package com.example.bustracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class showBus extends AppCompatActivity {

    RecyclerView recview;
    myadapter adapter;
    ProgressBar bar;

    @Override
    public void onBackPressed() {

         startActivity(new Intent(getApplicationContext(),MainActivity.class));
//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bus);
        bar=(ProgressBar)findViewById(R.id.progressBar3);
        bar.setVisibility(View.VISIBLE);
//        setTitle("Search Buses..");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(true);

//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayShowTitleEnabled(false);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bar.setVisibility(View.INVISIBLE);
            }
        },4000);


        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BusDetails"), model.class)
                        .build();

//

        adapter=new myadapter(options,getApplicationContext());
        recview.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recview.addItemDecoration(dividerItemDecoration);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });





        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BusDetails").orderByChild("busNumber").startAt(s).endAt(s+"\uf8ff"), model.class)
                        .build();

        adapter=new myadapter(options,getApplicationContext());
        adapter.startListening();
        recview.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "User Signed Out", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                return true;
            case R.id.addBus:
//                Intent addBus = new Intent(MainActivity.this,addBus.class);
//                startActivity(addBus);
                  startActivity(new Intent(getApplicationContext(),addBus.class));
                  return true;

            case R.id.showAllBus:
//                Intent showBus = new Intent(MainActivity.this,showBus.class);
//                startActivity(showBus);
                  startActivity(new Intent(getApplicationContext(),showBus.class));
                  return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}