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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author vini
 */
@Entity
@PrimaryKeyJoinColumn(name = "cpf")
@Table(name = "motorista")
public class Motorista extends Funcionario implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "cnh")
    private String cnh;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cpfMotorista")
    private List<Problema> problemaList;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "motorista" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Corrida> corridaList;
    @JoinColumn(name = "cpf", referencedColumnName = "cpf", insertable = false, updatable = false)
    @OneToOne

    private Funcionario funcionario;

    public Motorista() {
    }

    public Motorista(String cpf) {
        this.cpf = cpf;
    }

    public Motorista(String cpf, String cnh) {
        this.cpf = cpf;
        this.cnh = cnh;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public List<Problema> getProblemaList() {
        return problemaList;
    }

    public void setProblemaList(List<Problema> problemaList) {
        this.problemaList = problemaList;
    }

    public List<Corrida> getCorridaList() {
        return corridaList;
    }

    public void setCorridaList(List<Corrida> corridaList) {
        this.corridaList = corridaList;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpf != null ? cpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motorista)) {
            return false;
        }
        Motorista other = (Motorista) object;
        if ((this.cpf == null && other.cpf != null) || (this.cpf != null && !this.cpf.equals(other.cpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Motorista{" + "cpf=" + cpf + ", cnh=" + cnh + ", problemaList=" + problemaList + ", corridaList=" + corridaList + ", funcionario=" + funcionario + '}';
    }
    

    
}