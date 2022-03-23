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
@Table(name = "tipoproblema")
@NamedQueries({
    @NamedQuery(name = "Tipoproblema.findAll", query = "SELECT t FROM Tipoproblema t"),
    @NamedQuery(name = "Tipoproblema.findById", query = "SELECT t FROM Tipoproblema t WHERE t.id = :id"),
    @NamedQuery(name = "Tipoproblema.findByTipo", query = "SELECT t FROM Tipoproblema t WHERE t.tipo = :tipo")})
public class TipoProblema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipo")
    private List<Problema> problemaList;

    public TipoProblema() {
    }

    public TipoProblema(Integer id) {
        this.id = id;
    }

    public TipoProblema(Integer id, String tipo) {
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

    public List<Problema> getProblemaList() {
        return problemaList;
    }

    public void setProblemaList(List<Problema> problemaList) {
        this.problemaList = problemaList;
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
        if (!(object instanceof TipoProblema)) {
            return false;
        }
        TipoProblema other = (TipoProblema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistema.model.pojo.Tipoproblema[ id=" + id + " ]";
    }
    
}
