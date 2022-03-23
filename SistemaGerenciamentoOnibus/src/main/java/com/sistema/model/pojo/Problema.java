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
import javax.persistence.Lob;
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
@Table(name = "problema")
@NamedQueries({
    @NamedQuery(name = "Problema.findAll", query = "SELECT p FROM Problema p"),
    @NamedQuery(name = "Problema.findById", query = "SELECT p FROM Problema p WHERE p.id = :id"),
    @NamedQuery(name = "Problema.findByDataHorario", query = "SELECT p FROM Problema p WHERE p.dataHorario = :dataHorario")})
public class Problema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "data_horario")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHorario;
    @JoinColumn(name = "cpf_motorista", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Motorista cpfMotorista;
    @JoinColumn(name = "placa_onibus", referencedColumnName = "placa")
    @ManyToOne(optional = false)
    private Onibus placaOnibus;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoProblema idTipo;

    public Problema() {
    }

    public Problema(Integer id) {
        this.id = id;
    }

    public Problema(Integer id, Date dataHorario) {
        this.id = id;
        this.dataHorario = dataHorario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(Date dataHorario) {
        this.dataHorario = dataHorario;
    }

    public Motorista getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(Motorista cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public Onibus getPlacaOnibus() {
        return placaOnibus;
    }

    public void setPlacaOnibus(Onibus placaOnibus) {
        this.placaOnibus = placaOnibus;
    }

    public TipoProblema getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoProblema idTipo) {
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
        if (!(object instanceof Problema)) {
            return false;
        }
        Problema other = (Problema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistema.model.pojo.Problema[ id=" + id + " ]";
    }
    
}
