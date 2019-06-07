package com.skynet.ateu.medcarry.fragments;


import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.activity.FormularioActivity;
import com.skynet.ateu.medcarry.activity.UsuarioActivity;
import com.skynet.ateu.medcarry.modelo.Cliente;
import com.skynet.ateu.medcarry.util.Validacoes;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;




public class FormularioFragment extends Fragment{
    private static final String TAG = "FormularioActivity";

    private boolean informationCheck = false;
    private boolean newUser = false;
    private boolean isALogin;

    private FirebaseAuth mAuth;
    private Cliente mCliente;

    private DatabaseReference ref;

    private byte[] uploadBytes;

    private String mNome;
    private String mCpf;
    private String mData;
    private String mTelefone;
    private String mEmail;

    private EditText etNome;
    private EditText etCpf;
    private EditText etData;
    private EditText etTelefone;
    private Button mCheckButton;
    private View mView;

    private int dataTextLastLength = 0;
    private int cpfTextLastLenght = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formulario,container,false);
        mView = view;
        initViews();



        etData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String current = charSequence.toString();
                if(current.length() > 2){
                    etData.removeTextChangedListener(this);
                    String replace = current.replace("/","");
                    Log.d(TAG, "onTextChanged: "+dataTextLastLength+" "+replace.length()+" "+replace);
                    if(replace.length() == 3 && replace.length() > dataTextLastLength){
                        current = replace.split("")[1]+replace.split("")[2]+"/"+replace.split("")[3];
                    }else if(replace.length() == 5 && replace.length() > dataTextLastLength){
                        current = replace.split("")[1]+replace.split("")[2]+"/"+replace.split("")[3]+replace.split("")[4]+"/"+replace.split("")[5];
                    }



                    etData.setText(current);
                    etData.setSelection(current.length());
                    etData.addTextChangedListener(this);
                }
                dataTextLastLength = current.replace("/","").length();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etCpf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String current = charSequence.toString();
                if(current.length() > 3){
                    etCpf.removeTextChangedListener(this);
                    String replace = current.replace(".","");
                    replace = replace.replace("-","");

                    if(replace.length() == 4 && replace.length() > cpfTextLastLenght){
                        current = replace.split("")[1]+replace.split("")[2]+replace.split("")[3]+"."+replace.split("")[4];
                    }else if (replace.length() == 7 && replace.length() > cpfTextLastLenght){
                        current = replace.split("")[1]+replace.split("")[2]+replace.split("")[3]+"."+replace.split("")[4]
                                +replace.split("")[5]+replace.split("")[6]+"."+replace.split("")[7];
                    }else if(replace.length() == 10 && replace.length() > cpfTextLastLenght){
                        current = replace.split("")[1]+replace.split("")[2]+replace.split("")[3]+"."+replace.split("")[4]
                                +replace.split("")[5]+replace.split("")[6]+"."+replace.split("")[7]+replace.split("")[8]
                                +replace.split("")[9]+"-"+replace.split("")[10];
                    }

                    etCpf.setText(current);
                    etCpf.setSelection(current.length());

                    etCpf.addTextChangedListener(this);
                }

                cpfTextLastLenght = current.replace("-","").replace(".","").length();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etTelefone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"onClick : check informations");
                checkIsEmpty();
                if(informationCheck){
                    attInformations();
                    if(newUser){
                        Intent intent = new Intent(getActivity(),UsuarioActivity.class);
                        startActivity(intent);
                    }
                    getActivity().finish();
                }

            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
    private void attInformations() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child("Client").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("1");
        ref.child("nome").setValue(etNome.getText().toString());
        ref.child("cpf").setValue(etCpf.getText().toString());
        ref.child("telefone").setValue(etTelefone.getText().toString());
        ref.child("data").setValue(etData.getText().toString());

    }

    private void initViews() {
        etCpf =  mView.findViewById(R.id.et_formulario_cpf);
        etNome =  mView.findViewById(R.id.et_formulario_nome);
        etData =  mView.findViewById(R.id.et_formulario_data);
        etTelefone =  mView.findViewById(R.id.et_formulario_telefone);
        mCheckButton = mView.findViewById(R.id.bt_formulairo_continuar);
    }


    private void checaInformacoes(){

        checkCpf();
        if(informationCheck){
            checkTelefone();
        }
    }
    private void checkIsEmpty(){
        if(etNome.getText().toString().length() > 0
                && etCpf.getText().toString().length() > 0
                && etData.getText().toString().length() > 0
                && etTelefone.getText().toString().length() > 0){
            Log.i(TAG,"checkIsEmpty: todas as informaçoes foram preenchidas");
            checaInformacoes();
        }else{
            Log.i(TAG,"checkIsEmpty: informaçoes nao preenchidas");
            makeToast("Preencha todas as informaçoes");

        }
    }

    private void checkCpf(){
        if(Validacoes.validaCpf(etCpf.getText().toString().replace("-","").replace(".",""))){
            Log.i(TAG,"checkCpf: cpf valido");
            informationCheck = true;
        }else{
            Log.i(TAG,"checkCpf: cpf invalido");
            informationCheck = false;
            etCpf.setTextColor(Color.RED);
            makeToast("Cpf invalido");
        }
    }
    private void checkTelefone(){
        if(Validacoes.validaTelefone(etTelefone.getText().toString())){
            Log.i(TAG,"checkTelefone: telefone valido");
            informationCheck = true;
        }else{
            Log.i(TAG,"checkTelefone: telefone invalido");
            informationCheck = false;
            makeToast("Numero de telefone invalido");
        }
    }


    @SuppressLint("NewApi")
    private void makeToast(String txt){
        Toast.makeText(getActivity(),txt,Toast.LENGTH_SHORT).show();
    }


}
