/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sistema.model.pojo;


public class Onibus {
    private String placa;
    private int ano;
    private float quilometragem;
    private String modelo;

    public Onibus(String placa, int ano, float quilometragem, String modelo) {
        this.placa = placa;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.modelo = modelo;
    }

    public Onibus() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(float quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}