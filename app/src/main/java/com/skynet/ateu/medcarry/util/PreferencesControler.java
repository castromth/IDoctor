package com.skynet.ateu.medcarry.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PreferencesControler {
    private static final String TAG = "PreferencesControler";
    SharedPreferences preferences;

    public PreferencesControler(Context context,String prefName,int mode){
        Log.d(TAG, "PreferencesControler: "+prefName);
        preferences = context.getSharedPreferences(prefName,mode);

    }
    public void addNewPreference(String chave,String valor){
        Log.d(TAG, "addNewPreference: "+valor);
        preferences.edit().putString(chave,valor).commit();
    }
    public void addNewPreference(String chave,int valor){
        Log.d(TAG, "addNewPreference: "+chave+" "+valor);
        preferences.edit().putInt(chave,valor).commit();
    }
    public void addNewPreference(String chave,boolean valor){
        Log.d(TAG, "addNewPreference: "+chave+" "+valor);
        preferences.edit().putBoolean(chave,valor).commit();
    }

    public String getPreference(String valor){
        Log.d(TAG, "getPreference: "+valor);
        return preferences.getString(valor,"").toString();
    }

}
