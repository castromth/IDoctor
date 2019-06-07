package com.skynet.ateu.medcarry.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skynet.ateu.medcarry.R;

public class ConsultaActivity extends AppCompatActivity {
    private Button btVolta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        btVolta = findViewById(R.id.bt_consulta_volta);
        btVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
