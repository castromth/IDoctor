package com.skynet.ateu.medcarry.interfaces;

import android.view.View;

public interface RecyclerViewClickListenerHack {
    public void onMedicRvClickListener(View view, int position);
    public void onEspecialidadeRvClickListener(View view,int position);
    public void onSearchRvClickListener(View view,int position);
    void onReceitaRvClickListener(View view,int position);
    void onConsultaRvClickListener(View view,int pósition);
}
