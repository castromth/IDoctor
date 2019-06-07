package com.skynet.ateu.medcarry.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.adapter.SearchAdapter;
import com.skynet.ateu.medcarry.interfaces.RecyclerViewClickListenerHack;
import com.skynet.ateu.medcarry.modelo.Medico;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener,RecyclerViewClickListenerHack{
    private static final String TAG = "SearchActivity";
    private boolean pesquiso = false;

    private RecyclerView searchRv;
    private EditText searchText;
    private Button bt_volta;

    private SearchAdapter searchAdapter;

    private Query searchQuery;
    private FirebaseRecyclerOptions<Medico> medicoOptions;
    private DatabaseReference mMedicRef;
    private Query medicoQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchRv = findViewById(R.id.rv_search);
        bt_volta = findViewById(R.id.bt_search_volta);
        bt_volta.setOnClickListener(this);
        searchText = findViewById(R.id.et_search);
        mMedicRef = FirebaseDatabase.getInstance().getReference("Medics");
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    searchAction(searchText.getText().toString());
                    return true;
                }
                return false;
            }
        });


        if(getIntent() != null){
            if(getIntent().getExtras() != null){
                String text = getIntent().getExtras().getString("especialidade");
                if(text != null){
                    searchAction(text);
                    searchText.setText(text);
                    searchText.setSelection(text.length());
                }
            }

        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        if(pesquiso){
            searchAdapter.stopListening();
        }
    }

    private void searchAction(String searchText){
        Log.i(TAG,"serachAction "+searchText);
        searchQuery = mMedicRef.orderByChild("nome").startAt(searchText).endAt(searchText+"\uf8ff");
        medicoOptions = new FirebaseRecyclerOptions.Builder<Medico>()
                .setQuery(searchQuery,Medico.class)
                .build();
        initRv(medicoOptions);

    }
    private void initRv(FirebaseRecyclerOptions<Medico> options){
        searchRv = findViewById(R.id.rv_search);
        searchRv.setHasFixedSize(true);

        searchAdapter = new SearchAdapter(options);
        searchRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        searchAdapter.setRecyclerViewClickListenerHack(this);

        searchRv.setAdapter(searchAdapter);
        searchRv.setVisibility(View.VISIBLE);
        searchAdapter.startListening();
        pesquiso = true;
    }
    @Override
    public void onMedicRvClickListener(View view, int position) {

    }

    @Override
    public void onEspecialidadeRvClickListener(View view, int position) {

    }

    @Override
    public void onSearchRvClickListener(View view, int position) {

    }

    @Override
    public void onReceitaRvClickListener(View view, int position) {

    }

    @Override
    public void onConsultaRvClickListener(View view, int pósition) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_search_volta:
                finish();
                break;
        }
    }
}
