package com.skynet.ateu.medcarry.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.adapter.ReceitaAdapter;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.skynet.ateu.medcarry.modelo.Receita;

public class ReceitasActivity extends AppCompatActivity implements RecyclerViewClickListenerHack {

    private static final String TAG = "ReceitasActivity";

    private RecyclerView rvReceitas;
    private ReceitaAdapter mReceitaAdapter;
    private Button mButton;


    private DatabaseReference mReceitaRef;
    private Query mQuery;
    private FirebaseRecyclerOptions<Receita> mReceitaOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);
        mButton = findViewById(R.id.bt_receitas_volta);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mReceitaRef = FirebaseDatabase.getInstance().getReference("Users").child("Client").child(uid).child("Receitas");
        mQuery = mReceitaRef.orderByKey();

        mReceitaOptions =new FirebaseRecyclerOptions.Builder<Receita>()
                .setQuery(mQuery, Receita.class)
                .build();
        rvReceitas = findViewById(R.id.rv_receitas);
        mReceitaAdapter = new ReceitaAdapter(mReceitaOptions);
        mReceitaAdapter.setRecyclerViewClickListenerHack(this);

        rvReceitas.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvReceitas.setLayoutManager(llm);
        rvReceitas.setAdapter(mReceitaAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mReceitaAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mReceitaAdapter.stopListening();
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
        Log.i(TAG,"onReceitaRvClickListener");
        Intent intent = new Intent(this,ReceitaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConsultaRvClickListener(View view, int pósition) {

    }
}
