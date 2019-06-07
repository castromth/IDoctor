package com.skynet.ateu.medcarry.modelo;

public class CartaoCredito {

    private String nome;
    private String numero;
    private String dataVenc;
    private String codigoVerificador;

    public CartaoCredito(){

    }


    public String getNome() {
        return this.nome;
    }

    public String getCodigoVerificador() {
        return this.codigoVerificador;
    }

    public String getDataVenc() {
        return this.dataVenc;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigoVerificador(String codigoVerificador) {
        this.codigoVerificador = codigoVerificador;
    }

    public void setDataVenc(String dataVenc) {
        this.dataVenc = dataVenc;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
