/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sistema.model.pojo;

import java.time.LocalDateTime;


public class Problema {
    private int id, idTipo;
    private String descricao;
    private LocalDateTime dataHorario;
    private Onibus onibus;
    private Motorista motorista;

    public Problema(int id, int idTipo, String descricao, LocalDateTime dataHorario, Onibus onibus, Motorista motorista) {
        this.id = id;
        this.idTipo = idTipo;
        this.descricao = descricao;
        this.dataHorario = dataHorario;
        this.onibus = onibus;
        this.motorista = motorista;
    }

    public Problema() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(LocalDateTime dataHorario) {
        this.dataHorario = dataHorario;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }
    
}