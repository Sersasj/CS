/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vini
 */
@Entity
@Table(name = "tipoemergencia")
@NamedQueries({
    @NamedQuery(name = "Tipoemergencia.findAll", query = "SELECT t FROM Tipoemergencia t"),
    @NamedQuery(name = "Tipoemergencia.findById", query = "SELECT t FROM Tipoemergencia t WHERE t.id = :id"),
    @NamedQuery(name = "Tipoemergencia.findByTipo", query = "SELECT t FROM Tipoemergencia t WHERE t.tipo = :tipo")})
public class TipoEmergencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipo")
    private List<Emergencia> emergenciaList;

    public TipoEmergencia() {
    }

    public TipoEmergencia(Integer id) {
        this.id = id;
    }

    public TipoEmergencia(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Emergencia> getEmergenciaList() {
        return emergenciaList;
    }

    public void setEmergenciaList(List<Emergencia> emergenciaList) {
        this.emergenciaList = emergenciaList;
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
        if (!(object instanceof TipoEmergencia)) {
            return false;
        }
        TipoEmergencia other = (TipoEmergencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistema.model.pojo.Tipoemergencia[ id=" + id + " ]";
    }
    
}
