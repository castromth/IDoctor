package com.skynet.ateu.medcarry.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.interfaces.ConsultasRvClickListener;
import com.skynet.ateu.medcarry.modelo.Consulta;

import java.util.List;

public class ConsultasAdpter extends RecyclerView.Adapter<ConsultaViewHolder> {
    private final static String TAG = "ConsultaAdpter";
    private List<Consulta> mConsultas;


    private ConsultasRvClickListener mConsultaRvClickListener;

    public ConsultasAdpter(List<Consulta> list){
        mConsultas = list;
    }

    @NonNull
    @Override
    public ConsultaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreatViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consultas,parent,false);
        return new ConsultaViewHolder(view, mConsultaRvClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultaViewHolder holder, int position) {
        Log.i(TAG,"onBindViewHolder");
        Consulta model = mConsultas.get(position);
        holder.setInformations(model.getMedicoNome(),
                model.getEspecialidade(),
                model.getData(),
                model.getHorario(),
                model.getJaAconteceu());
    }

    @Override
    public int getItemCount() {
        return mConsultas != null ? mConsultas.size() : 0;
    }

    public void setConsultaRvClickListener(ConsultasRvClickListener r) {
        this.mConsultaRvClickListener = r;
    }
    public void updateList(List<Consulta> list){
        mConsultas = list;
        notifyDataSetChanged();
    }
    public void addNewItem(Consulta consulta){
        mConsultas.add(consulta);
        notifyItemInserted(mConsultas.size()-1);
    }
    public void addNewItens(List<Consulta> consultas){
        int i = mConsultas.size()-1;
        mConsultas.addAll(consultas);
        notifyItemRangeInserted(i,mConsultas.size()-1-i);
    }

}
