/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sistema.model.dominio;

/**
 *
 * @author Renan
 */
public class Motorista extends Funcionario{
    private String cnh;

    public Motorista(String cnh, String cpf, String rg, String nome, String telefone, String endereco) {
        super(cpf, rg, nome, telefone, endereco);
        this.cnh = cnh;
    }

    public Motorista() {
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
    
}