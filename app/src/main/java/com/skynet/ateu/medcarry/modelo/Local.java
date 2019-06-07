package com.skynet.ateu.medcarry.modelo;

public class Local {
    private String lat;
    private String log;
    private String endereco;


    public Local(){

    }


    public void setLog(String log) {
        this.log = log;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLog() {
        return this.log;
    }

    public String getLat() {
        return this.lat;
    }

    public String getEndereco() {
        return this.endereco;
    }
}
