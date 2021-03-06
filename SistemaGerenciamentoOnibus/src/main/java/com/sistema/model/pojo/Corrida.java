/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author vini
 */
@Entity
@Table(name = "Corrida")
public class Corrida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "inicio_corrida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioCorrida;
    @Basic(optional = false)
    @Column(name = "fim_corrida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fimCorrida;
    @Column(name = "pass_nao_pagantes")
    private Integer passNaoPagantes;
    @Column(name = "pass_pagantes")
    private Integer passPagantes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "consumo_combustivel")
    private Float consumoCombustivel;
    @Column(name = "distancia_percorrida")
    private Float distanciaPercorrida;
    @JoinColumn(name = "num_linha", referencedColumnName = "numero")
    @ManyToOne(optional = false)
    private Linha linha;
    @JoinColumn(name = "cpf_motorista", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Motorista motorista;
    @JoinColumn(name = "placa_onibus", referencedColumnName = "placa")
    @ManyToOne(optional = false)
    private Onibus onibus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCorrida")
    private List<Emergencia> emergenciaList;
    @Transient
    private float latitude, longitude;

    public Corrida() {
    }

    public Corrida(Integer id) {
        this.id = id;
    }

    public Corrida(Integer id, Date inicioCorrida, Date fimCorrida) {
        this.id = id;
        this.inicioCorrida = inicioCorrida;
        this.fimCorrida = fimCorrida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInicioCorrida() {
        return inicioCorrida;
    }

    public void setInicioCorrida(Date inicioCorrida) {
        this.inicioCorrida = inicioCorrida;
    }

    public Date getFimCorrida() {
        return fimCorrida;
    }

    public void setFimCorrida(Date fimCorrida) {
        this.fimCorrida = fimCorrida;
    }

    public Integer getPassNaoPagantes() {
        return passNaoPagantes;
    }

    public void setPassNaoPagantes(Integer passNaoPagantes) {
        this.passNaoPagantes = passNaoPagantes;
    }

    public Integer getPassPagantes() {
        return passPagantes;
    }

    public void setPassPagantes(Integer passPagantes) {
        this.passPagantes = passPagantes;
    }

    public Float getConsumoCombustivel() {
        return consumoCombustivel;
    }

    public void setConsumoCombustivel(Float consumoCombustivel) {
        this.consumoCombustivel = consumoCombustivel;
    }

    public Float getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setDistanciaPercorrida(Float distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public List<Emergencia> getEmergenciaList() {
        return emergenciaList;
    }

    public void setEmergenciaList(List<Emergencia> emergenciaList) {
        this.emergenciaList = emergenciaList;
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
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corrida)) {
            return false;
        }
        Corrida other = (Corrida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Corrida{" + "id=" + id + ", linha=" + linha + ", motorista=" + motorista + ", onibus=" + onibus + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

    public String coordenadasToString() {
        return "" + latitude + ", " + longitude;
    }
    
}