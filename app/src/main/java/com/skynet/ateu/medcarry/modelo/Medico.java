package com.skynet.ateu.medcarry.modelo;

import java.util.List;

public class Medico extends User {
    private String especialidade;
    private String subEspecialidade;
    private List<Local> locais;
    private String uid;

    public Medico(){

    }
    public Medico(String nome, String numeroTelefone) {
        super(nome, numeroTelefone);
    }


    public List<Local> getLocais() {
        return locais;
    }

    public String getUid() {
        return this.uid;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public String getSubEspecialidade() {
        return this.subEspecialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setSubEspecialidade(String subEspecialidade) {
        this.subEspecialidade = subEspecialidade;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
