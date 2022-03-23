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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vini
 */
@Entity
@Table(name = "linhaponto")
@NamedQueries({
    @NamedQuery(name = "Linhaponto.findAll", query = "SELECT l FROM Linhaponto l"),
    @NamedQuery(name = "Linhaponto.findById", query = "SELECT l FROM Linhaponto l WHERE l.id = :id")})
public class LinhaPonto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLinhaPonto")
    private List<HorarioEsperado> horarioesperadoList;
    @JoinColumn(name = "num_linha", referencedColumnName = "numero")
    @ManyToOne(optional = false)
    private Linha numLinha;
    @JoinColumn(name = "id_ponto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ponto idPonto;

    public LinhaPonto() {
    }

    public LinhaPonto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<HorarioEsperado> getHorarioesperadoList() {
        return horarioesperadoList;
    }

    public void setHorarioesperadoList(List<HorarioEsperado> horarioesperadoList) {
        this.horarioesperadoList = horarioesperadoList;
    }

    public Linha getNumLinha() {
        return numLinha;
    }

    public void setNumLinha(Linha numLinha) {
        this.numLinha = numLinha;
    }

    public Ponto getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(Ponto idPonto) {
        this.idPonto = idPonto;
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
        if (!(object instanceof LinhaPonto)) {
            return false;
        }
        LinhaPonto other = (LinhaPonto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistema.model.pojo.Linhaponto[ id=" + id + " ]";
    }
    
}
