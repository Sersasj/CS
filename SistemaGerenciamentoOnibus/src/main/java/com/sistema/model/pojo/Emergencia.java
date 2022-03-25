/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vini
 */
@Entity
@Table(name = "emergencia")
public class Emergencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data_horario")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHorario;
    @Basic(optional = false)
    @Column(name = "latitude")
    private float latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private float longitude;
    @JoinColumn(name = "id_corrida", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Corrida idCorrida;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoEmergencia idTipo;

    public Emergencia() {
    }

    public Emergencia(Integer id) {
        this.id = id;
    }

    public Emergencia(Integer id, Date dataHorario, float latitude, float longitude) {
        this.id = id;
        this.dataHorario = dataHorario;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(Date dataHorario) {
        this.dataHorario = dataHorario;
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

    public Corrida getIdCorrida() {
        return idCorrida;
    }

    public void setIdCorrida(Corrida idCorrida) {
        this.idCorrida = idCorrida;
    }

    public TipoEmergencia getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoEmergencia idTipo) {
        this.idTipo = idTipo;
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
        if (!(object instanceof Emergencia)) {
            return false;
        }
        Emergencia other = (Emergencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistema.model.pojo.Emergencia[ id=" + id + " ]";
    }
    
}
