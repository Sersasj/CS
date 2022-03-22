/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sistema.model.pojo;
import java.time.LocalDateTime;


public class Corrida {
    private int id, passageirosPagantes, passageirosNaoPagantes;
    private float consumoCombustivel, distanciaPercorrida;
    private Motorista Motorista;
    private Onibus onibus;
    private Linha linha;
    private LocalDateTime inicioCorrida, fimCorrida;

    public Corrida(int id, int passageirosPagantes, int passageirosNaoPagantes, float consumoCombustivel, float distanciaPercorrida, Motorista Motorista, Onibus onibus, Linha linha, LocalDateTime inicioCorrida, LocalDateTime fimCorrida) {
        this.id = id;
        this.passageirosPagantes = passageirosPagantes;
        this.passageirosNaoPagantes = passageirosNaoPagantes;
        this.consumoCombustivel = consumoCombustivel;
        this.distanciaPercorrida = distanciaPercorrida;
        this.Motorista = Motorista;
        this.onibus = onibus;
        this.linha = linha;
        this.inicioCorrida = inicioCorrida;
        this.fimCorrida = fimCorrida;
    }

    public Corrida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassageirosPagantes() {
        return passageirosPagantes;
    }

    public void setPassageirosPagantes(int passageirosPagantes) {
        this.passageirosPagantes = passageirosPagantes;
    }

    public int getPassageirosNaoPagantes() {
        return passageirosNaoPagantes;
    }

    public void setPassageirosNaoPagantes(int passageirosNaoPagantes) {
        this.passageirosNaoPagantes = passageirosNaoPagantes;
    }

    public float getConsumoCombustivel() {
        return consumoCombustivel;
    }

    public void setConsumoCombustivel(float consumoCombustivel) {
        this.consumoCombustivel = consumoCombustivel;
    }

    public float getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setDistanciaPercorrida(float distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

    public Motorista getMotorista() {
        return Motorista;
    }

    public void setMotorista(Motorista Motorista) {
        this.Motorista = Motorista;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public LocalDateTime getInicioCorrida() {
        return inicioCorrida;
    }

    public void setInicioCorrida(LocalDateTime inicioCorrida) {
        this.inicioCorrida = inicioCorrida;
    }

    public LocalDateTime getFimCorrida() {
        return fimCorrida;
    }

    public void setFimCorrida(LocalDateTime fimCorrida) {
        this.fimCorrida = fimCorrida;
    }
    
    
}