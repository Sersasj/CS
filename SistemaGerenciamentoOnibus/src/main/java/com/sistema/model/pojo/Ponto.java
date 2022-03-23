/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vini
 */
@Entity
@Table(name = "ponto")
@NamedQueries({
    @NamedQuery(name = "Ponto.findAll", query = "SELECT p FROM Ponto p"),
    @NamedQuery(name = "Ponto.findById", query = "SELECT p FROM Ponto p WHERE p.id = :id"),
    @NamedQuery(name = "Ponto.findByLatitude", query = "SELECT p FROM Ponto p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "Ponto.findByLongitude", query = "SELECT p FROM Ponto p WHERE p.longitude = :longitude")})
public class Ponto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "latitude")
    private float latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private float longitude;
    @ManyToMany(mappedBy = "pontoList")
    private List<Linha> linhaList;

    public Ponto() {
    }

    public Ponto(Integer id) {
        this.id = id;
    }

    public Ponto(Integer id, float latitude, float longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Linha> getLinhaList() {
        return linhaList;
    }

    public void setLinhaList(List<Linha> linhaList) {
        this.linhaList = linhaList;
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
        if (!(object instanceof Ponto)) {
            return false;
        }
        Ponto other = (Ponto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistema.model.pojo.Ponto[ id=" + id + " ]";
    }
    
}
