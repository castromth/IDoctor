package com.skynet.ateu.medcarry.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.skynet.ateu.medcarry.modelo.Consulta;

import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends SQLiteOpenHelper {

    private static final String TAG = "ConsultaDAO";
    private String userUid;
    private String tableName;
    SQLiteDatabase db;


    private static int version = 3;
    public ConsultaDAO(Context context) {
        super(context, "Consulta", null, version);
        Log.d(TAG, "ConsultaDAO: ");
        userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        tableName = "CONSULTAS"+userUid;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: "+tableName);
        String sql = "CREATE TABLE "+tableName+"(id INTEGER PRIMARY KEY," +
                "data DATETIME," +
                "especialidade TEXT NOT NULL," +
                "subespecialidade TEXT," +
                "local TEXT," +
                "medico TEXT NOT NULL," +
                "medicoUid TEXT," +
                "tipo TEXT," +
                "jaAconteceu TEXT NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+tableName+";";
        sqLiteDatabase.execSQL(sql);
        version++;
        onCreate(sqLiteDatabase);
    }

    public void dropTable(){
        onUpgrade(getWritableDatabase(),version,version++);
    }
    public void insere(Consulta consulta){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("data", consulta.getData());
        dados.put("especialidade", consulta.getEspecialidade());
        dados.put("subespecialidade", consulta.getSubEspecialide());
        dados.put("local", consulta.getLocal());
        dados.put("medico", consulta.getMedicoNome());
        dados.put("medicoUid", consulta.getMedicoUID());
        dados.put("tipo", consulta.getTipo());
        dados.put("jaAconteceu", consulta.getJaAconteceu());

        db.insert(tableName, null, dados);
    }
    public void insereAll(List<Consulta> consultas){

        for(Consulta consulta:consultas){
            insere(consulta);
        }


    }

    public List<Consulta> buscaConsultas(){

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+tableName+" ORDER BY data;", null);
        List<Consulta> consultas = new ArrayList<>();
        while (c.moveToNext()){
            Consulta consulta = new Consulta();
            consulta.setData(c.getString(c.getColumnIndex("data")).toString());
            consulta.setEspecialidade(c.getString(c.getColumnIndex("especialidade")).toString());
            consulta.setMedicoNome(c.getString(c.getColumnIndex("medico")).toString());
            consulta.setJaAconteceu(c.getString(c.getColumnIndex("jaAconteceu")).toString());
            consultas.add(consulta);
            }
            c.close();
        return consultas;
    }

}
