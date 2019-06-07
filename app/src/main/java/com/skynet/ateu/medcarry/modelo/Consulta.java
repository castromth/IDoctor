package com.skynet.ateu.medcarry.modelo;

public class Consulta {
    private String data;
    private String horario;
    private String especialidade;
    private String subEspecialide;
    private String medicoNome;

    private String receitaUID;
    private String medicoUID;

    private String local;
    private String timestamp;
    private String tipo;
    private String jaAconteceu = "0";

    public Consulta(){

    }
    public Consulta(String data,String horario,String endereco,String tipo){
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSubEspecialide() {
        return this.subEspecialide;
    }

    public String getMedicoNome() {
        return this.medicoNome;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public String getData() {
        return this.data;
    }
    public String getHorario() {
        return this.horario;
    }
    public String getMedicoUID() {
        return medicoUID;
    }
    public String getLocal() {
        return local;
    }
    public String getTipo() {
        return this.tipo;
    }

    public String getJaAconteceu() {
        return jaAconteceu;
    }

    public void setJaAconteceu(String jaAconteceu) {
        this.jaAconteceu = jaAconteceu;
    }

    public void setSubEspecialide(String subEspecialide) {
        this.subEspecialide = subEspecialide;
    }
    public void setMedicoNome(String medicoNome) {
        this.medicoNome = medicoNome;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setMedicoUID(String medicoUID) {
        this.medicoUID = medicoUID;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
