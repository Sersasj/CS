/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sistema.model.pojo;

import java.util.List;


public class Linha {
    private int numero;
    private String nome;
    private List<Ponto> pontos;

    public Linha(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public Linha() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ponto> getPontos() {
        return pontos;
    }

    public void setPontos(List<Ponto> pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return "" + numero + " - " + nome;
    }
    
}