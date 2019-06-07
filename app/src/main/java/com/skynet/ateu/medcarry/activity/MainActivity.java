package com.skynet.ateu.medcarry.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skynet.ateu.medcarry.R;
import com.skynet.ateu.medcarry.fragments.ConsultasFragment;
import com.skynet.ateu.medcarry.fragments.PerfilFragment;
import com.skynet.ateu.medcarry.fragments.SearchFragment;


public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private static final int PERMISSIONS_REQUEST_CODE = 2631;
    private FirebaseUser mUser;
    private DatabaseReference ref;
    private String uid;

    private GeoFire geoFire;
    private LocationManager mLocationManager;


    private boolean logado = false;
    private Location mLocation;

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private SearchFragment searchFragment;
    private PerfilFragment perfilFragment;
    private ConsultasFragment consultasFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.main_nav);
        frameLayout = findViewById(R.id.main_frame);

        searchFragment = new SearchFragment();
        perfilFragment = new PerfilFragment();
        consultasFragment = new ConsultasFragment();

        setFragment(searchFragment);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_consultas:
                        setFragment(consultasFragment);
                        return true;
                    case R.id.nav_perfil:
                        setPerfilFragment();
                        return true;
                    case R.id.nav_medicos:
                        setFragment(searchFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });





    }

    private void setPerfilFragment() {
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            setFragment(perfilFragment);
        }else{
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser != null) {
            logado = true;
            uid = mUser.getUid();
            getLocation();
        }else{
            logado = false;
        }
        Log.i(TAG, "onStart");
    }


    public void LoginActivity(View view) {
        if (!logado) {
            Log.i(TAG,"Login");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Log.i(TAG,"Usuario");
            Intent intent = new Intent(this, UsuarioActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void initViews() {


    }

    private void verificaPermicoes() {
        Log.i(TAG, "pedindo permiçoes para o usuario");
        String[] permisoes = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permisoes[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permisoes[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permisoes[2]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permisoes[3]) == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(MainActivity.this, permisoes, PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        verificaPermicoes();
    }


    private void getLocation() {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mLocation = mLocationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        ref = FirebaseDatabase.getInstance().getReference("GeoFire").child("Client");
        geoFire = new GeoFire(ref);
        geoFire.setLocation(uid, new GeoLocation(mLocation.getLatitude(), mLocation.getLongitude()), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                Log.i(TAG,"getLocation: geofire setlocation complete");
            }
        });
        Log.i(TAG,"getLocation: localizaçao salva");

    }

}
