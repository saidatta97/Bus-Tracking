package com.example.bustracking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    public static final int RC_SIGN_IN = 1;
    TextView text;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.PhoneBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);

//        text=findViewById(R.id.text);
//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(MainActivity.this,addBus.class);
//                startActivity(i);
//
//            }
//        });

        mFirebaseAuth=FirebaseAuth.getInstance();
        mAuthStateListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if (user!=null)
                {
                    Toast.makeText(MainActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(providers)
//                          .setLogo(R.drawable.bus)
                            .build(), RC_SIGN_IN);

                }
            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListner);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MainActivity.this, "User Signed Out", Toast.LENGTH_SHORT).show();
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

            case R.id.update:
//                Intent showBus = new Intent(MainActivity.this,showBus.class);
//                startActivity(showBus);
                startActivity(new Intent(getApplicationContext(),update.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

//    public void addBus(View view) {
//        Intent i = new Intent(MainActivity.this,addBus.class);
//
//        startActivity(i);
//
//    }
}