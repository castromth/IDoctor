package com.skynet.ateu.medcarry.listener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.skynet.ateu.medcarry.modelo.Cliente;

public class UserChildEventListener implements ChildEventListener {
    private static final String TAG = "UserChildEventListener";
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Cliente cliente = dataSnapshot.child("Users").getValue(Cliente.class);
        Log.i(TAG,"ADDED");
        Log.i(TAG,"Name"+cliente.getNome());
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Cliente cliente = dataSnapshot.child("Users").getValue(Cliente.class);
        Log.i(TAG,"CHANGED");
        Log.i(TAG,"Name"+cliente.getNome());
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        Cliente cliente = dataSnapshot.child("Users").getValue(Cliente.class);
        Log.i(TAG,"REMOVED");
        Log.i(TAG,"Name"+cliente.getNome());
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Cliente cliente = dataSnapshot.child("Users").getValue(Cliente.class);
        Log.i(TAG,"MOVED");
        Log.i(TAG,"Name"+cliente.getNome());
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
