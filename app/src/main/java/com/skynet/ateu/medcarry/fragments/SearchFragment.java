package com.skynet.ateu.medcarry.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.activity.SearchActivity;
import com.skynet.ateu.medcarry.adapter.EspecialidadeAdapter;
import com.skynet.ateu.medcarry.interfaces.EspecialidadesRvClickListener;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.skynet.ateu.medcarry.modelo.Especialidade;
import com.skynet.ateu.medcarry.util.FirebaseReferenceControler;
import com.skynet.ateu.medcarry.util.PreferencesControler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.android.gms.flags.impl.SharedPreferencesFactory.getSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements EspecialidadesRvClickListener {

    private static final String TAG = "SearchFragment";


    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;

    private DatabaseReference mEspeciRef;
    private EspecialidadeAdapter especialidadeAdapter;
    private FirebaseReferenceControler mReferenceControler;

    private PreferencesControler mPrefCont;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search, container, false);
        constraintLayout = v.findViewById(R.id.search_search_layout);
        recyclerView = v.findViewById(R.id.rv_especialidades);
        mReferenceControler = new FirebaseReferenceControler();
        mEspeciRef = FirebaseDatabase.getInstance().getReference().child("Especialidades");
        mPrefCont = new PreferencesControler(getActivity().getBaseContext(),"pref",Context.MODE_PRIVATE);
        updateEspecialidades();


        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchAction(view);
            }
        });
        return v;
    }

    private void updateEspecialidades() {
        String string = mPrefCont.getPreference("especialidade");
        if(string.length() > 1){
            String[] stringarray = string.split(",");
            List<String> list = new ArrayList<>(Arrays.asList(stringarray));
            initRv(list);
            getEspecialidadesList();
        }else{
            getEspecialidadesList();
        }
    }

    private void initRv(List<String> list) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        especialidadeAdapter = new EspecialidadeAdapter(list);
        especialidadeAdapter.setEspecialidadesRvClickListener(this);
        recyclerView.setAdapter(especialidadeAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
    public void onSearchAction(View view){
        Intent intent = new Intent(getContext(), SearchActivity.class);
        startActivity(intent);
    }


    @Override
    public void onEspecialidadeClickListener(String especilidade) {
        Intent intent = new Intent(getContext(),SearchActivity.class);
        intent.putExtra("especialidade",especilidade);

        startActivity(intent);
    }

    public void getEspecialidadesList() {
        Log.d(TAG,"getEspecialidade");
        final ArrayList<String> mList = new ArrayList<>();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder builder = new StringBuilder();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    mList.add(dataSnapshot1.getValue(Especialidade.class).getNome());
                    builder.append(mList.get(mList.size()-1)).append(",");
                }
                initRv(mList);
                updateEspecialidades(builder.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mReferenceControler.getEspecialidadesRef().addListenerForSingleValueEvent(listener);
    }

    private void updateEspecialidades(String s) {
        Log.d(TAG,"updateEspecialidades");
        mPrefCont.addNewPreference("especialidade",s);
    }
}
