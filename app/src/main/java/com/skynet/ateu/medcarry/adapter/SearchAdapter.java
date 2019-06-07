package com.skynet.ateu.medcarry.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.skynet.ateu.medcarry.modelo.Medico;

public class SearchAdapter extends FirebaseRecyclerAdapter<Medico,SerachItemViewHolder> {
    private static final String TAG = "SearchAdapter";
    private RecyclerViewClickListenerHack mRecyclerViewClickListenerHack;

    public SearchAdapter(@NonNull FirebaseRecyclerOptions<Medico> options) {
        super(options);
        Log.i(TAG,"Criando");
    }

    public void setRecyclerViewClickListenerHack(RecyclerViewClickListenerHack r){
        mRecyclerViewClickListenerHack = r;
    }

    @Override
    protected void onBindViewHolder(@NonNull SerachItemViewHolder holder, int position, @NonNull Medico model) {
        Log.i(TAG,"onBindViewHolder");
        holder.setInformations(model.getNome().toString(),
                model.getEspecialidade(),
                model.getSubEspecialidade(),
                model.getFotoUrl());
    }

    @NonNull
    @Override
    public SerachItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        return new SerachItemViewHolder(inflate,mRecyclerViewClickListenerHack,parent.getContext());
    }
}
