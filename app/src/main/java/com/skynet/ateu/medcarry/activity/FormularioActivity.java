package com.skynet.ateu.medcarry.activity;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.fragments.FormularioFragment;
import com.skynet.ateu.medcarry.fragments.SelecionaFoto;

public class FormularioActivity extends AppCompatActivity {
    private static final String TAG = "FormularioActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);



    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");

    }

}
