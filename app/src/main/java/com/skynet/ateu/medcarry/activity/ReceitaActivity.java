package com.skynet.ateu.medcarry.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.fragments.AtestadoFragment;
import com.skynet.ateu.medcarry.fragments.OrientacaoFragment;
import com.skynet.ateu.medcarry.fragments.ReceitaViewPagerAdpter;
import com.skynet.ateu.medcarry.fragments.RemedioFragment;

public class ReceitaActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private AppBarLayout mAppBarLayout;
    private ViewPager mViewPager;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        mButton = findViewById(R.id.bt_receita_volta);
        mTabLayout = findViewById(R.id.tbLayout_receita);
        mAppBarLayout = findViewById(R.id.appBar_receita);
        mViewPager = findViewById(R.id.vp_receita);
        ReceitaViewPagerAdpter mAdpter = new ReceitaViewPagerAdpter(getSupportFragmentManager());
        mAdpter.AddFragment(new OrientacaoFragment(),"Orientaçao");
        mAdpter.AddFragment(new RemedioFragment(),"Remedios");
        mAdpter.AddFragment(new AtestadoFragment(),"Atestado");

        mViewPager.setAdapter(mAdpter);
        mTabLayout.setupWithViewPager(mViewPager);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
