package com.skynet.ateu.medcarry.modelo;

public class Endereco {
    private String latitude;
    private String longitude;


    public Endereco(){

    }
    public Endereco(String latitude,String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
