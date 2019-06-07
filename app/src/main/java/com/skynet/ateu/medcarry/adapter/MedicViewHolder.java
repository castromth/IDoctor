package com.skynet.ateu.medcarry.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;

public class MedicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tv_nome;
    public TextView tv_especialidade;
    public TextView tv_subespecialidade;
    public TextView tv_nota;
    public ImageView iv_profile_img;
    private RecyclerViewClickListenerHack mRecyclerViewClickListenerHack;
    public MedicViewHolder(View itemView, RecyclerViewClickListenerHack r) {
        super(itemView);
        mRecyclerViewClickListenerHack = r;
        itemView.setOnClickListener(this);
        tv_nome = itemView.findViewById(R.id.tv_name);
        tv_especialidade = itemView.findViewById(R.id.tv_especialidade);
        tv_subespecialidade = itemView.findViewById(R.id.tv_subespecialidade);
        tv_nota = itemView.findViewById(R.id.tv_nota);
        iv_profile_img = itemView.findViewById(R.id.iv_foto);
    }


    @Override
    public void onClick(View view) {
        if(mRecyclerViewClickListenerHack != null){
            mRecyclerViewClickListenerHack.onMedicRvClickListener(itemView,getAdapterPosition());
        }
    }
}
