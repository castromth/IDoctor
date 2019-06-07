package com.skynet.ateu.medcarry.modelo;

import java.util.List;

public class Receita {

    private String medicoUID;
    private String consultaUID;
    private List<String> remediosUID;
    private String pacientUID;
    private String orientacao;


    private String medicoNome;
    private String especialidade;
    private String subEspecialide;
    private String data_emitido;
    private String data_termino;

    private String time;

    private String valida;
    public Receita(){

    }

    public String getPacientUID() {
        return pacientUID;
    }

    public String getTime() {
        return time;
    }

    public String getMedicoUID() {
        return medicoUID;
    }

    public List<String> getRemedios() {
        return remediosUID;
    }

    public String getConsultaUID() {
        return consultaUID;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getData_emitido() {
        return data_emitido;
    }

    public String getData_termino() {
        return data_termino;
    }

    public String getMedicoNome() {
        return medicoNome;
    }

    public String getSubEspecialide() {
        return subEspecialide;
    }

    public String getValida() {
        return valida;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setValida(String valida) {
        this.valida = valida;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setData_emitido(String data_emitido) {
        this.data_emitido = data_emitido;
    }

    public void setData_termino(String data_termino) {
        this.data_termino = data_termino;
    }

    public void setMedicoNome(String medicoNome) {
        this.medicoNome = medicoNome;
    }

    public void setSubEspecialide(String subEspecialide) {
        this.subEspecialide = subEspecialide;
    }

    public void setMedicoUID(String medicoUID) {
        this.medicoUID = medicoUID;
    }

    public void setConsultaUID(String consultaUID) {
        this.consultaUID = consultaUID;
    }

    public void setPacientUID(String pacientUID) {
        this.pacientUID = pacientUID;
    }

    public void setRemediosUID(List<String> remediosUID) {
        this.remediosUID = remediosUID;
    }
}
