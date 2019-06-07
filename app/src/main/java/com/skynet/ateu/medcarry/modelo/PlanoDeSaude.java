package com.skynet.ateu.medcarry.modelo;

public class PlanoDeSaude {
    private String registro;
    private String nome;


    public PlanoDeSaude(){}
    public PlanoDeSaude(String nome,String registro){
        this.nome = nome;
        this.registro = registro;
    }


    public String getNome() {
        return this.nome;
    }
    public String getRegistro() {
        return this.registro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}
