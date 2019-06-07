package com.skynet.ateu.medcarry.modelo;

import java.util.List;

class Clinica extends CentroMedico {

    private Especialidade especialidade;

    public Clinica(String nome,Endereco endereco,List<Medico>medicos,Especialidade especialidade){
        super(nome,endereco,medicos);
        this.especialidade = especialidade;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }
}
