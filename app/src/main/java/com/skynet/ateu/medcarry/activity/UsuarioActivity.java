package com.skynet.ateu.medcarry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.modelo.Cliente;
import com.skynet.ateu.medcarry.modelo.Consulta;
import com.skynet.ateu.medcarry.modelo.PlanoDeSaude;
import com.skynet.ateu.medcarry.modelo.Receita;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsuarioActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "UsuarioActivity";

    private Cliente mCliente;


    private CircleImageView mProfileImage;
    private TextView mProfileName;


    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        initViews();
        if(mCliente != null){
            updateUI();
        }


    }

    private void initViews() {
        mProfileImage = findViewById(R.id.iv_usuario_photo);
        mProfileName = findViewById(R.id.tv_usuario_nome);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            mUser = mAuth.getCurrentUser();
            Log.i(TAG,"User: "+mUser.getUid());
            getUser(mUser);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_desloga:
            mAuth.signOut();
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.i(TAG,"SignOut Complete");
                        }
                    });
            finish();
            break;
        }

    }

    private void onConsultas() {
        Intent intent = new Intent(this,ConsultasActivity.class);
        startActivity(intent);

    }

    private void onPlanoSaude() {
        startActivity(new Intent(this,PlanoActivity.class));
    }
    public void onDados(){
        Intent intent = new Intent(this,DadosActivity.class);
        startActivity(intent);
    }
    private void onReceitas(){
        Intent intent = new Intent(this,ReceitasActivity.class);
        startActivity(intent);
    }

    private void getUser(FirebaseUser mUser){
        ref = FirebaseDatabase.getInstance().getReference("Users").child("Client");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Cliente cliente = dataSnapshot.getValue(Cliente.class);
                Log.i(TAG,"getUser: "+cliente.getNome());
                mCliente = cliente;
                updateUI();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        ref.child(mUser.getUid()).child("1").addListenerForSingleValueEvent(eventListener);
    }
    private void updateUI(){
        Log.i(TAG,"Cliente: "+ mCliente.getNome());
        updateProfilePhoto();
        mProfileName.setText(mCliente.getNome());
    }


    private void updateProfilePhoto() {
        Log.i(TAG,"updateUI: profileimage load");
        if(mCliente.getFotoUrl() != null){
            if(mCliente.getFotoUrl().equalsIgnoreCase("1")){
                Picasso.get().load(R.drawable.profile_photo_h).into(mProfileImage);
            }else{
                 Picasso.get().load(R.drawable.profile_photo_m).into(mProfileImage);
            }
        }else{
            Picasso.get().load(R.drawable.default_avatar).into(mProfileImage);
        }

    }

    private void makeToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

}
