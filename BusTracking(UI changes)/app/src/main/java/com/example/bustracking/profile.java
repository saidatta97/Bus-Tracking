package com.example.bustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {

    TextInputLayout fName,lName,email;
    ImageView profile;
    @Override
    public void onBackPressed() {

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fName= (TextInputLayout) findViewById(R.id.fName);
        lName=(TextInputLayout) findViewById(R.id.lName);
        email= (TextInputLayout)findViewById(R.id.email);
        profile= (ImageView)findViewById(R.id.image);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
//            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();

//            fName.setEdiText(acct.getDisplayName());
            fName.getEditText().setText(acct.getGivenName());
            lName.getEditText().setText(acct.getFamilyName());
            email.getEditText().setText(acct.getEmail());

            Picasso.get().load(acct.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(profile);
        }

    }
}