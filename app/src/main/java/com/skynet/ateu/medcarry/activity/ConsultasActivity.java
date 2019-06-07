package com.skynet.ateu.medcarry.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.adapter.ConsultasAdpter;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.skynet.ateu.medcarry.modelo.Consulta;

public class ConsultasActivity extends AppCompatActivity implements RecyclerViewClickListenerHack {
    private RecyclerView rvConsultas;
    private Button mButton;

    private ConsultasAdpter mConsultasAdpter;
    private FirebaseRecyclerOptions<Consulta> mOptions;
    private Query mQuery;
    private DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
//
//        mButton = findViewById(R.id.bt_consultas_volta);
//        rvConsultas = findViewById(R.id.rv_consultas);
//
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        ref = FirebaseDatabase.getInstance().getReference("Users").child("Client").child(uid).child("Consulta");
//        mQuery = ref.orderByPriority();
//        mOptions = new FirebaseRecyclerOptions.Builder<Consulta>()
//                .setQuery(mQuery, Consulta.class)
//                .build();
//        mConsultasAdpter.setmRecyclerViewClickListenerHack(this);
//
//        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        rvConsultas.setLayoutManager(llm);
//        rvConsultas.setHasFixedSize(true);
//        rvConsultas.setAdapter(mConsultasAdpter);






    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onMedicRvClickListener(View view, int position) {

    }
    @Override
    public void onEspecialidadeRvClickListener(View view, int position) {

    }
    @Override
    public void onSearchRvClickListener(View view, int position) {

    }
    @Override
    public void onReceitaRvClickListener(View view, int position) {

    }
    @Override
    public void onConsultaRvClickListener(View view, int pósition) {
        Intent intent = new Intent(this,ConsultaActivity.class);
        startActivity(intent);
    }
}
