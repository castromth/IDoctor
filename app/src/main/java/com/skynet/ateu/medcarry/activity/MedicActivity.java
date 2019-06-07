package com.skynet.ateu.medcarry.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.modelo.Medico;

import de.hdodenhof.circleimageview.CircleImageView;

public class MedicActivity extends AppCompatActivity {
    private DatabaseReference mRef;
    private static String medicUID;

    private TextView tv_nome;
    private TextView tv_especialidade;
    private TextView tv_subespecialidade;
    private TextView tv_telefone;
    private CircleImageView iv_profile;
    private Button bt_marcar;
    private Button bt_volta;
    private MapView mv_local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic);
        initViews();
        bt_volta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        if(intent != null){
            Bundle params = intent.getExtras();
            if(params != null){
                medicUID = params.getString("uid");
                initMedic(medicUID);
            }
        }
    }

    private void initMedic(String medicUID){
        mRef = FirebaseDatabase.getInstance().getReference("Medics").child(medicUID);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Medico medico = dataSnapshot.getValue(Medico.class);
                tv_nome.setText(medico.getNome().toString());
                tv_especialidade.setText(medico.getEspecialidade());
                tv_subespecialidade.setText(medico.getSubEspecialidade());
                tv_telefone.setText(medico.getTelefone().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void initViews(){
        tv_nome = findViewById(R.id.tv_nome_medico);
        tv_especialidade = findViewById(R.id.tv_especialidade_medico);
        tv_subespecialidade = findViewById(R.id.tv_subespecialidade_medico);
        tv_telefone = findViewById(R.id.tv_telefone_medico);
        iv_profile = findViewById(R.id.iv_profile_medico);
        bt_marcar = findViewById(R.id.bt_marcar_medico);
        bt_volta = findViewById(R.id.bt_medic_volta);
//        mv_local = findViewById(R.id.mv_local_medico);

    }
}
