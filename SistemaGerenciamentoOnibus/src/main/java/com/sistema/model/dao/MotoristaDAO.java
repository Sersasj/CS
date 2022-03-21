/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Motorista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vini
 */
public class MotoristaDAO {
    private Connection connection;

    public MotoristaDAO() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Motorista motorista) {
        String sql1 = "INSERT INTO funcionario(cpf, rg, nome, telefone, endereco) VALUES(?,?,?,?,?)";
        String sql2 = "INSERT INTO motorista(cpf, cnh) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql1);
            stmt.setString(1, motorista.getCpf());
            stmt.setString(2, motorista.getRg());
            stmt.setString(3, motorista.getNome());
            stmt.setString(4, motorista.getTelefone());
            stmt.setString(5, motorista.getEndereco());
            stmt.execute();
            stmt = connection.prepareStatement(sql2);
            stmt.setString(1, motorista.getCpf());
            stmt.setString(2, motorista.getCnh());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MotoristaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Motorista motorista) {
        String sql1 = "UPDATE funcionario SET rg=?, nome=?, telefone=?, endereco=? WHERE cpf=?";
        String sql2 = "UPDATE motorista SET cnh=? WHERE cpf=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql1);
            stmt.setString(1, motorista.getRg());
            stmt.setString(2, motorista.getNome());
            stmt.setString(3, motorista.getTelefone());
            stmt.setString(4, motorista.getEndereco());
            stmt.execute();
            stmt = connection.prepareStatement(sql2);
            stmt.setString(1, motorista.getCnh());
            stmt.setString(2, motorista.getCpf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MotoristaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Remover motorista pode não ser possível, pois tem corridas ligadas a ele
    public boolean remover(Motorista motorista) {
        String sql1 = "DELETE FROM motorista WHERE cpf=?";
        String sql2 = "DELETE FROM funcionario WHERE cpf=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql1);
            stmt.setString(1, motorista.getCpf());
            stmt.execute();
            stmt = connection.prepareStatement(sql2);
            stmt.setString(1, motorista.getCpf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MotoristaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Motorista> listar() {
        String sql = "SELECT * FROM motorista NATURAL JOIN funcionario";
        List<Motorista> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Motorista motorista = new Motorista();
                motorista.setCpf(resultado.getString("cpf"));
                motorista.setRg(resultado.getString("rg"));
                motorista.setNome(resultado.getString("nome"));
                motorista.setTelefone(resultado.getString("telefone"));
                motorista.setEndereco(resultado.getString("endereco"));
                motorista.setCnh(resultado.getString("cnh"));
                retorno.add(motorista);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MotoristaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Motorista buscar(Motorista motorista) {
        String sql = "SELECT * FROM motorista WHERE cpf=?";
        Motorista retorno = new Motorista();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, motorista.getCpf());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                motorista.setCpf(resultado.getString("cpf"));
                motorista.setRg(resultado.getString("rg"));
                motorista.setNome(resultado.getString("nome"));
                motorista.setTelefone(resultado.getString("telefone"));
                motorista.setEndereco(resultado.getString("endereco"));
                motorista.setCnh(resultado.getString("cnh"));
                retorno = motorista;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MotoristaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
