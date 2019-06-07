package com.skynet.ateu.medcarry.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.modelo.Cliente;
import com.skynet.ateu.medcarry.util.PreferencesControler;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
    private static final String TAG = "PerfilFragment";


    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    private FirebaseUser  mUser;

    private CircleImageView mProfileImage;
    private TextView textView;
    private Button btSair;
    private Button btFicha;
    private Button btPlano;
    private Button btConsultas;
    private Button btReceitas;

    private Cliente mCliente;
    private PreferencesControler mPrefCont;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        mProfileImage = v.findViewById(R.id.iv_usuario_photo);
        textView = v.findViewById(R.id.tv_usuario_nome);
        mPrefCont = new PreferencesControler(getActivity().getBaseContext(),"pref", Context.MODE_PRIVATE);
        updateUI();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");


    }
    private void getUser(FirebaseUser mUser){

        ref = FirebaseDatabase.getInstance().getReference("Users").child("Client");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Cliente cliente = dataSnapshot.getValue(Cliente.class);
                Log.i(TAG,"getUser: "+cliente.getNome());
                mCliente = cliente;
                mPrefCont.addNewPreference("usuario",mCliente.getNome());
                mPrefCont.addNewPreference("foto",mCliente.getFotoUrl());
                updateUI();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        ref.child(mUser.getUid()).child("1").addListenerForSingleValueEvent(eventListener);
    }
    private void updateUI() {
        Log.i(TAG,"updateUI: ");
        String nomeusuario = mPrefCont.getPreference("usuario").toString().split(" ")[0 ];
        String profileimage = mPrefCont.getPreference("foto").toString();
        if(nomeusuario.length() > 1){
            textView.setText(nomeusuario.toString());
            if(profileimage == "1"){
                Picasso.get().load(R.drawable.profile_photo_m).into(mProfileImage);
            }else{
                Picasso.get().load(R.drawable.profile_photo_h).into(mProfileImage);
            }
        }else{
            mAuth = FirebaseAuth.getInstance();
            if(mAuth.getCurrentUser() != null){
                mUser = mAuth.getCurrentUser();
                Log.i(TAG,"User: "+mUser.getUid());
                getUser(mUser);
            }
        }
    }

}
