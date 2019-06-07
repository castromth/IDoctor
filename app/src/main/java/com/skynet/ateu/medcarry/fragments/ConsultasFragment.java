package com.skynet.ateu.medcarry.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.adapter.ConsultasAdpter;
import com.skynet.ateu.medcarry.dao.ConsultaDAO;
import com.skynet.ateu.medcarry.interfaces.ConsultasRvClickListener;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.skynet.ateu.medcarry.modelo.Consulta;
import com.skynet.ateu.medcarry.util.FirebaseReferenceControler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultasFragment extends Fragment implements ConsultasRvClickListener {
    private static final String TAG = "ConsultasFragment";

    private RecyclerView recyclerView;
    private ConsultasAdpter consultasAdpter;

    private List<Consulta> consultas;
    private int consultaSize;
    private int consultaExtSize;
    private List<Consulta> newConsultas = new ArrayList<>();

    private ConsultaDAO dao;
    private FirebaseReferenceControler controlerRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_consultas, container, false);
        recyclerView = v.findViewById(R.id.rv_consultas);
        dao = new ConsultaDAO(getActivity().getBaseContext());
        consultas = dao.buscaConsultas();
        consultaSize = consultas.size();
        dao.close();
        controlerRef = new FirebaseReferenceControler();

        initRv(consultas);
        getConsultasN();



        return v;
    }
    private void getConsultasN(){
        Log.d(TAG,"getConsultaN");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                consultaExtSize = (int) dataSnapshot.getChildrenCount();
                if(consultaExtSize - consultaSize > 0){
                    getConsultas();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        controlerRef.getConsultaRef().orderByChild("timestamp").addListenerForSingleValueEvent(listener);
    }
    private void getConsultas() {
        Log.d(TAG, "getConsultas: ");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    newConsultas.add(dataSnapshot1.getValue(Consulta.class));
                }
                if(newConsultas.size() > 0){
                    updateConsultasDAO(newConsultas);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        controlerRef.getConsultaRef().orderByChild("timestamp").addValueEventListener(listener);
    }


    private void updateConsultasDAO(List<Consulta> list) {
        dao = new ConsultaDAO(getActivity().getBaseContext());
        dao.insereAll(list);
        consultasAdpter.addNewItens(list);

    }

    private void initRv(List<Consulta> list){
        Log.d(TAG, "initRv: ");
        consultasAdpter = new ConsultasAdpter(list);
        consultasAdpter.setConsultaRvClickListener(this);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(consultasAdpter);
    }

    @Override
    public void onConsultasClickListener(View view, int adapterPosition) {

    }
}
