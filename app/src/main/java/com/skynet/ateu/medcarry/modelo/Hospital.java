package com.skynet.ateu.medcarry.modelo;

import java.util.List;

public class Hospital extends CentroMedico {

    private List<Especialidade> especialidades;


    public Hospital(String nome, Endereco endereco, List<Medico> medicos,List<Especialidade> especialidades) {
        super(nome, endereco, medicos);
        this.especialidades = especialidades;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }
}
