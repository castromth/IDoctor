package com.skynet.ateu.medcarry.util;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseReferenceControler {
    private static final String TAG = "FirebaseReferenceContro";
    private FirebaseDatabase mDB;
    private static final String ESPECIALIDADES_PATH = "Especialidades" ;
    private static final String USUARIOS_PATH = "Users/Client";



    public FirebaseReferenceControler(){
        mDB = FirebaseDatabase.getInstance();
    }


    public DatabaseReference getUsuarioRef() {
        String UserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return mDB.getReference(USUARIOS_PATH+"/"+UserUID);
    }
    public DatabaseReference getEspecialidadesRef(){

        return mDB.getReference(ESPECIALIDADES_PATH);
    }

    public DatabaseReference getConsultaRef() {
        String CONSULTAS_PATH = USUARIOS_PATH+"/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/"+"Consulta";
        Log.d(TAG, "getConsultaRef: "+FirebaseAuth.getInstance().getCurrentUser().getUid());
        return mDB.getReference(CONSULTAS_PATH);
    }
}
