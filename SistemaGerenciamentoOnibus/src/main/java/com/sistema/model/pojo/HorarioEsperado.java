/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "HorarioEsperado")
public class HorarioEsperado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "horario")
    @Temporal(TemporalType.TIME)
    private Date horario;
    @JoinColumn(name = "id_linha_ponto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LinhaPonto idLinhaPonto;

    public HorarioEsperado() {
    }

    public HorarioEsperado(Integer id) {
        this.id = id;
    }

    public HorarioEsperado(Integer id, Date horario) {
        this.id = id;
        this.horario = horario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public LinhaPonto getIdLinhaPonto() {
        return idLinhaPonto;
    }

    public void setIdLinhaPonto(LinhaPonto idLinhaPonto) {
        this.idLinhaPonto = idLinhaPonto;
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
        if (!(object instanceof HorarioEsperado)) {
            return false;
        }
        HorarioEsperado other = (HorarioEsperado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistema.model.pojo.Horarioesperado[ id=" + id + " ]";
    }
    
}
