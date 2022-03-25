/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Funcionario;

/**
 *
 * @author sergi
 */
public class FuncionarioDAO extends GenericDAO<Funcionario, String>{
    public FuncionarioDAO(){
        super(Funcionario.class);
    }
    
}
