package com.skynet.ateu.medcarry.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.adapter.EspecialidadeAdapter;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.skynet.ateu.medcarry.modelo.Especialidade;

public class EspecialidadeFragment extends Fragment implements RecyclerViewClickListenerHack {
    private static final String TAG = "EspecialidadeFrag";

    private DatabaseReference mDatabase;
    private EspecialidadeAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.especialidade_fragment_layout,container,false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Especialidades");
        mDatabase.keepSynced(true);

        DatabaseReference medicosRef = FirebaseDatabase.getInstance().getReference().child("Especialidades");
        Query personsQuery = medicosRef.orderByKey();
        FirebaseRecyclerOptions<Especialidade> options =
                new FirebaseRecyclerOptions.Builder<Especialidade>()
                        .setQuery(personsQuery, Especialidade.class)
                        .build();
        init(view,options);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i(TAG,"onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    private void init(View view, FirebaseRecyclerOptions<Especialidade> options) {
//        RecyclerView rvEspecialidades = view.findViewById(R.id.recycleview_especialidade);
//        rvEspecialidades.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rvEspecialidades.setLayoutManager(llm);
//        mAdapter = new EspecialidadeAdapter(options);
//        rvEspecialidades.setAdapter(mAdapter);
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

    }
}
