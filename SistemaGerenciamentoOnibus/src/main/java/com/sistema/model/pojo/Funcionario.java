/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sistema.model.pojo;

/**
 *
 * @author Renan
 */
public class Funcionario {
    private String cpf;
    private String rg;
    private String nome;
    private String telefone;
    private String endereco;

    public Funcionario(String cpf, String rg, String nome, String telefone, String endereco) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Funcionario() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}