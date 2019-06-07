package com.skynet.ateu.medcarry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SerachItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tv_nome;
    private TextView tv_especialidade;
    private TextView tv_subEspcialidade;
    private CircleImageView iv_foto;
    private RecyclerViewClickListenerHack r;
    private Context context;
    public SerachItemViewHolder(View itemView, RecyclerViewClickListenerHack mRecyclerViewClickListenerHack,Context c) {
        super(itemView);
        r = mRecyclerViewClickListenerHack;
        context = c;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        tv_nome = itemView.findViewById(R.id.tv_searchitem_nome);
        tv_especialidade = itemView.findViewById(R.id.tv_searchitem_espci);
        tv_subEspcialidade = itemView.findViewById(R.id.tv_searchitem_subespci);
        iv_foto = itemView.findViewById(R.id.iv_searchitem_foto);
    }

    public void setInformations(String nome,String especialidade,String subEspecialidade,String url){
        tv_nome.setText(nome.toString());
        tv_especialidade.setText(especialidade.toString());
        tv_subEspcialidade.setText(subEspecialidade);
        Picasso.get().load(url).into(iv_foto);
    }

    @Override
    public void onClick(View view) {
        if(r != null){
            r.onSearchRvClickListener(view,getAdapterPosition());
        }
    }
}
