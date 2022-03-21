/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sistema.model.pojo;

import java.time.LocalDateTime;

/**
 *
 * @author Renan
 */
public class Emergencia {
    private int id, idTipo;
    private LocalDateTime dataHorario;
    private Corrida corrida;
    private float latitude, longitude;

    public Emergencia(int id, int idTipo, LocalDateTime dataHorario, Corrida corrida, float latitude, float longitude) {
        this.id = id;
        this.idTipo = idTipo;
        this.dataHorario = dataHorario;
        this.corrida = corrida;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Emergencia() {
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

    public LocalDateTime getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(LocalDateTime dataHorario) {
        this.dataHorario = dataHorario;
    }

    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    
}