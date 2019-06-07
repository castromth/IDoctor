package com.skynet.ateu.medcarry.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.modelo.Consulta;
import com.skynet.ateu.medcarry.util.FirebaseReferenceControler;
import com.skynet.ateu.medcarry.util.PreferencesControler;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SplashActivity extends AppCompatActivity {
    private static final int DELAY_SPLASH_SCREEN = 2000;
    private static final String TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        Consulta c = new Consulta();
//        c.setTimestamp("1547184600");
//        c.setMedicoNome("Dr. Ateu");
//        c.setEspecialidade("Ortopedia");
//        c.setData("11 DE JAN 2019");
//        c.setJaAconteceu("1");
//        c.setHorario("08:30");
//
//        FirebaseReferenceControler controlRef = new FirebaseReferenceControler();
//        String push = controlRef.getConsultaRef().push().getKey();
//        Log.d(TAG, "onCreate: "+push);
//        FirebaseDatabase.getInstance().getReference("Users/Client/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Consulta").child(push).setValue(c);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                finish();
            }
        },DELAY_SPLASH_SCREEN);



    }
}
