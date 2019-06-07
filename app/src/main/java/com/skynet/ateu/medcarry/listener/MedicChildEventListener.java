package com.skynet.ateu.medcarry.listener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.skynet.ateu.medcarry.modelo.Medico;

public class MedicChildEventListener implements ChildEventListener {
    private static final String TAG = "MedicChildEventListener";
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Medico medico = dataSnapshot.child("Medicos").getValue(Medico.class);
        Log.i(TAG,"ADDED");
        Log.i(TAG,"Name"+medico.getNome());
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Medico medico = dataSnapshot.child("Medicos").getValue(Medico.class);
        Log.i(TAG,"CHANGED");
        Log.i(TAG,"Name"+medico.getNome());
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        Medico medico = dataSnapshot.child("Medicos").getValue(Medico.class);
        Log.i(TAG,"REMOVED");
        Log.i(TAG,"Name"+medico.getNome());
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Medico medico = dataSnapshot.child("Medicos").getValue(Medico.class);
        Log.i(TAG,"MOVED");
        Log.i(TAG,"Name"+medico.getNome());
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
