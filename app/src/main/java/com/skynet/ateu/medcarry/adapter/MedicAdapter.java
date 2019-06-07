package com.skynet.ateu.medcarry.adapter;


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

public class MedicAdapter extends FirebaseRecyclerAdapter<Medico,MedicViewHolder>{
    private static final String TAG  = "MedicAdapter";
    private RecyclerViewClickListenerHack mRecyclerViewClickListenerHack;
    public MedicAdapter(@NonNull FirebaseRecyclerOptions<Medico> options) {
        super(options);
        Log.i(TAG,"Criando");

    }
    public void setRecyclerViewClickListenerHack(RecyclerViewClickListenerHack r){
        mRecyclerViewClickListenerHack = r;
    }
    @Override
    protected void onBindViewHolder(@NonNull MedicViewHolder holder, int position, @NonNull Medico model) {
        Log.i(TAG,"onBindViewHolder");
        holder.tv_nome.setText(model.getNome());
        holder.tv_especialidade.setText(model.getEspecialidade());
        holder.tv_subespecialidade.setText(model.getSubEspecialidade());

    }

    @NonNull
    @Override
    public MedicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medic,parent,false);
        return new MedicViewHolder(view,mRecyclerViewClickListenerHack);
    }

}
