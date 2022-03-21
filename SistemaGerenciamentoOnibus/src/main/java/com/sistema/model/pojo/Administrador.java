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
public class Administrador extends Funcionario{
    private int id;
    private String senha;

    public Administrador(int id, String senha, String cpf, String rg, String nome, String telefone, String endereco) {
        super(cpf, rg, nome, telefone, endereco);
        this.id = id;
        this.senha = senha;
    }

    public Administrador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}