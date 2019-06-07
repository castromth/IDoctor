package com.skynet.ateu.medcarry.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.adapter.MedicAdapter;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.skynet.ateu.medcarry.modelo.Medico;

import java.util.List;

public class MedicFragment extends Fragment implements RecyclerViewClickListenerHack {

    private MedicAdapter mMedicAdapter;
    private DatabaseReference mDatabase;
    public MedicFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.medic_fragment_layout,container,false);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Medicos");
        mDatabase.keepSynced(true);

        DatabaseReference medicosRef = FirebaseDatabase.getInstance().getReference().child("Medicos");
        Query personsQuery = medicosRef.orderByKey();
        FirebaseRecyclerOptions<Medico> options =
                new FirebaseRecyclerOptions.Builder<Medico>()
                        .setQuery(personsQuery, Medico.class)
                        .build();
        init(view,options);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("MedicFrag","começando a ouvir");
        mMedicAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMedicAdapter.stopListening();
    }

    public void init(View view, FirebaseRecyclerOptions<Medico> options){
        RecyclerView rvMedicos = view.findViewById(R.id.recycleview_medic);
        rvMedicos.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMedicos.setLayoutManager(llm);
        mMedicAdapter = new MedicAdapter(options);
        rvMedicos.setAdapter(mMedicAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

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
