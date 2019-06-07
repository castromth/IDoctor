package com.skynet.ateu.medcarry.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;

public class ReceitaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "ReceitaViewHolder";
    private TextView tv_nome_medico;
    private TextView tv_data_emitido;
    private TextView tv_data_termino;
    private TextView tv_especialidade;
    private TextView tv_subespecialidade;
    private TextView tv_valida;

    private RecyclerViewClickListenerHack mRecyclerViewClickListenerHack;
    public ReceitaViewHolder(View itemView, RecyclerViewClickListenerHack r) {
        super(itemView);
        mRecyclerViewClickListenerHack = r;
        itemView.setOnClickListener(this);
        tv_data_emitido = itemView.findViewById(R.id.tv_receitaRv_dataemitida);
        tv_data_termino = itemView.findViewById(R.id.tv_receitaRv_datatermino);
        tv_nome_medico = itemView.findViewById(R.id.tv_receitaRv_medico);
        tv_especialidade = itemView.findViewById(R.id.tv_receitaRv_Especialidade);
        tv_subespecialidade = itemView.findViewById(R.id.tv_receitaRv_subespci);
        tv_valida = itemView.findViewById(R.id.tv_receitaRv_Valida);
    }

    public void setInformations(String medico,String especialidade,String subespecialidade,String data_emitido,String data_termino,String valida){
        tv_valida.setText(valida);
        tv_subespecialidade.setText(subespecialidade);
        tv_especialidade.setText(especialidade);
        tv_nome_medico.setText(medico);
        tv_data_termino.setText(data_termino);
        tv_data_emitido.setText(data_emitido);

    }
    @Override
    public void onClick(View view) {
        Log.i(TAG,"onClick");
        if(mRecyclerViewClickListenerHack != null){
            mRecyclerViewClickListenerHack.onReceitaRvClickListener(view,getAdapterPosition());
        }
    }
}
