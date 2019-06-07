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
import com.skynet.ateu.medcarry.interfaces.EspecialidadesRvClickListener;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.skynet.ateu.medcarry.modelo.Especialidade;

import java.util.List;


public class EspecialidadeAdapter extends RecyclerView.Adapter<EspecialidadeViewHolder>{
    private static final String TAG = "EspecialidadeAdapter";
    private EspecialidadesRvClickListener r;
    private List<String> mEspecialidades;



    public EspecialidadeAdapter(List<String> especialidades){
        mEspecialidades = especialidades;
    }

    @NonNull
    @Override
    public EspecialidadeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_especialidade,parent,false);

        return new EspecialidadeViewHolder(view,r);
    }

    @Override
    public void onBindViewHolder(@NonNull EspecialidadeViewHolder holder, int position) {
        Log.i(TAG,"onBindViewHolder");
        holder.tvEspecialidade.setText(mEspecialidades.get(position));
    }

    @Override
    public int getItemCount() {
        return mEspecialidades != null ? mEspecialidades.size() : 0;
    }

    public void updateList(List<String> list){
        mEspecialidades = list;
        notifyDataSetChanged();
    }


    public void setEspecialidadesRvClickListener(EspecialidadesRvClickListener r) {
        this.r = r;
    }
}
