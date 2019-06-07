package com.skynet.ateu.medcarry.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.interfaces.ConsultasRvClickListener;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;

public class ConsultaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvNomeMedico;
    private TextView tvEspecialidade;
    private TextView tvData;
    private TextView tvHorario;
    private TextView tvStatus;

    private ConsultasRvClickListener mConsultasRvClickListener;

    public ConsultaViewHolder(View itemView, ConsultasRvClickListener r) {
        super(itemView);
        mConsultasRvClickListener = r;
        itemView.setOnClickListener(this);
        tvNomeMedico = itemView.findViewById(R.id.tv_cardConsulta_medico);
        tvEspecialidade = itemView.findViewById(R.id.tv_cardConsulta_especialidade);
        tvData = itemView.findViewById(R.id.tv_cardConsulta_data);
        tvHorario = itemView.findViewById(R.id.tv_cardConsultas_horario);
        tvStatus = itemView.findViewById(R.id.tv_cardConsulta_status);

    }


    public void setInformations(String medicoNome,String especialidade,String data,String horario,String status){
        tvHorario.setText(horario);
        tvData.setText(data);
        tvNomeMedico.setText(medicoNome);
        tvEspecialidade.setText(especialidade);
        if(status.equalsIgnoreCase("1")) tvStatus.setText("Realizada");
        else{
            tvStatus.setText("Agendada");
        }

    }

    @Override
    public void onClick(View view) {
        if(mConsultasRvClickListener != null){
            mConsultasRvClickListener.onConsultasClickListener(view,getAdapterPosition());
        }
    }
}
