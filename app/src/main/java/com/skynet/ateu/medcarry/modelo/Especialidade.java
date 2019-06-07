package com.skynet.ateu.medcarry.modelo;

import android.net.Uri;

public class Especialidade {
    private String nome = "";
    private String imageUri;
    public Especialidade(){
    }
    public Especialidade(String nome,String certificado){
        this.nome = nome;
    }

    public String getImageUri() {
        return imageUri;
    }
    public String getNome(){
        return this.nome;
    }
}
