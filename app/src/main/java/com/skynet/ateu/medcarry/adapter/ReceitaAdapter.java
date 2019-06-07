package com.skynet.ateu.medcarry.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.skynet.ateu.medcarry.modelo.Receita;

public class ReceitaAdapter extends FirebaseRecyclerAdapter<Receita,ReceitaViewHolder> {
    private final static String TAG = "ReceitaAdapter";

    private RecyclerViewClickListenerHack mRecyclerViewClickListenerHack;

    public ReceitaAdapter(@NonNull FirebaseRecyclerOptions<Receita> options) {
        super(options);
        Log.i(TAG,"Criando");

    }
    public void setRecyclerViewClickListenerHack(RecyclerViewClickListenerHack r){
        Log.i(TAG,"setClickListenert");
        mRecyclerViewClickListenerHack = r;
    }

    @Override
    protected void onBindViewHolder(@NonNull ReceitaViewHolder holder, int position, @NonNull Receita model) {
        Log.i(TAG,"onBindViewHolder");
        holder.setInformations(model.getMedicoNome(),model.getEspecialidade(),model.getSubEspecialide(),model.getData_emitido(),model.getData_termino(),model.getValida());

    }

    @NonNull
    @Override
    public ReceitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receita,parent,false);

        return new ReceitaViewHolder(view,mRecyclerViewClickListenerHack);
    }

}
