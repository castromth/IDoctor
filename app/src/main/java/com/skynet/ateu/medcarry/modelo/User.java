package com.skynet.ateu.medcarry.modelo;


public abstract class User {
    private String nome;
    private String telefone;
    private String cpf ;
    private String data;
    private String email;
    private String fotoUrl;

    public User(){

    }
    public User(String nome, String telefone){
        this.nome = nome;
        this.telefone = telefone;

    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setData(String dataNascimento) {
        this.data = dataNascimento;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getData() {
        return data;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getFotoUrl() {
        return fotoUrl;
    }
}
