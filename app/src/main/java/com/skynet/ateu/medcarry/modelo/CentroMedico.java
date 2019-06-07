package com.skynet.ateu.medcarry.modelo;

import java.util.List;

public abstract class CentroMedico {
    private String nome;
    private Endereco endereco;
    private List<Medico> medicos;

    public CentroMedico(String nome,Endereco endereco,List<Medico>medicos){
        this.endereco = endereco;
        this.nome = nome;
        this.medicos = medicos;
    }

    public String getNome(){
        return this.nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }
}
