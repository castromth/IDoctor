package com.skynet.ateu.medcarry.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.interfaces.EspecialidadesRvClickListener;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;

import de.hdodenhof.circleimageview.CircleImageView;

public class EspecialidadeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvEspecialidade;
    public CircleImageView ivEspecialidade;
    private EspecialidadesRvClickListener r;
    public EspecialidadeViewHolder(View itemView,EspecialidadesRvClickListener r) {
        super(itemView);
        this.r = r;
        itemView.setOnClickListener(this);
        tvEspecialidade = itemView.findViewById(R.id.tv_especialidade_horizontal_scroll_vew);
        ivEspecialidade = itemView.findViewById(R.id.iv_especialidade_horizontal_srcoll_view);
    }

    @Override
    public void onClick(View view) {
        if(r != null){
            r.onEspecialidadeClickListener(tvEspecialidade.getText().toString());
        }
    }
}
