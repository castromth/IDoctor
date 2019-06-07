package com.skynet.ateu.medcarry.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.modelo.PlanoDeSaude;

import java.util.Arrays;

public class PlanoActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "PlanoActivity";

    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    private DatabaseReference ref;
    private PlanoDeSaude mPlanoDeSaude;

    private Spinner mSpinner;
    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;
    private Button mVolta;
    private View mDivisor;

    private String planoNome = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano);
        initViews();
        initNewPlano();
    }

    private void initViews() {
        mSpinner = findViewById(R.id.sp_planos_plano);
        mEditText = findViewById(R.id.et_id_plano);
        mTextView = findViewById(R.id.tv_planodados_plano);
        mButton = findViewById(R.id.bt_salvar_planos);
        mButton.setOnClickListener(this);
        mVolta = findViewById(R.id.bt_volta_plano);
        mVolta.setOnClickListener(this);
        mDivisor = findViewById(R.id.v_plano_divisor);
    }


    private void initNewPlano() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planos_saude,R.layout.spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String[] mArray = getResources().getStringArray(R.array.planos_saude);
                planoNome = Arrays.asList(mArray).get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initPlano();
    }

    private void initPlano() {
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = mUser.getUid();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPlanoDeSaude = dataSnapshot.getValue(PlanoDeSaude.class);
                if(mPlanoDeSaude != null){
                    updateUI(true);
                }else{
                    updateUI(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref = FirebaseDatabase.getInstance().getReference("Users").child("Client").child(uid).child("Plano");
        ref.addListenerForSingleValueEvent(listener);
    }

    private void updateUI(boolean a) {
        if(a){
            mTextView.setText(planoFormatter(mPlanoDeSaude.getNome(),mPlanoDeSaude.getRegistro()));
            mTextView.setVisibility(View.VISIBLE);
            mButton.setText("Editar");
            mButton.setVisibility(View.VISIBLE);
            mDivisor.setVisibility(View.VISIBLE);

        }else{
            mSpinner.setVisibility(View.VISIBLE);
            mEditText.setVisibility(View.VISIBLE);
            mButton.setVisibility(View.VISIBLE);
            mDivisor.setVisibility(View.VISIBLE);

        }

    }
    private String planoFormatter(String nome,String registro){
        String planoText = nome+"  -  "+registro;

        return planoText;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_salvar_planos:
                onSalvaPlano();
                finish();
                break;
            case R.id.bt_volta_plano:
                finish();
                break;
        }
    }

    private void onSalvaPlano() {
        saveNewPlano();
    }
    private void saveNewPlano() {
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = mUser.getUid();
        mPlanoDeSaude = new PlanoDeSaude();
        if(mEditText.getText().toString().length() > 0){
            mEditText.setTextColor(getResources().getColor(R.color.colorSecondaryText));
            mPlanoDeSaude.setNome(planoNome);
            mPlanoDeSaude.setRegistro(mEditText.getText().toString());
            if(planoNome != null){
                ref = FirebaseDatabase.getInstance().getReference("Users").child("Client").child(uid).child("Plano");
                ref.setValue(mPlanoDeSaude);
            }else{
                Log.i(TAG,"plano de saude nao selecionado");
                makeToast("Escolha a provedora do plano");
            }
        }else{
            Log.i(TAG,"Id nao preenchido");
            makeToast("ID nao preenchido");
            mEditText.setTextColor(Color.RED);
        }

    }
    private void makeToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
