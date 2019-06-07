package com.skynet.ateu.medcarry.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.geofire.GeoFire;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.modelo.Cliente;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private static final int RC_SIGN_IN = 123;
    private FirebaseDatabase mDatabase;
    private DatabaseReference ref;
    private FirebaseUser mUser;
    private  AuthUI mAuthUI;
    private GeoFire geoFire;

    private boolean newUser = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AppTheme_Login)
                        .build(),
                RC_SIGN_IN);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "onActivityResult: ");
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                saveUser();
            }
        }else{
            Log.d(TAG, "onActivityResult: ");
        }
    }
    private void sucessSign(){

        finish();
    }
    private void saveUser(){
        Log.i(TAG,"saveUser");
        ref = FirebaseDatabase.getInstance().getReference("Users");
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        final Cliente cliente = new Cliente();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Cliente clientee = dataSnapshot.getValue(Cliente.class);
                if(clientee == null){
                    Log.i(TAG,"saveUser: new user");
                    newUser = true;
                    ref.child("Client").child(uid).child("1").setValue(cliente);
                    sucessSign();
                }else{
                    if(clientee.getNome() == null){
                        newUser = true;
                    }else{
                        Log.i(TAG,"saveUser: old user");
                        newUser = false;
                    }

                    sucessSign();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG,"ValueListener: error "+databaseError.getMessage());
            }
        };
        ref.child("Client").child(uid).child("1").addListenerForSingleValueEvent(listener);
    }
}
