/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.dominio.Onibus;
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
public class OnibusDAO {
    private Connection connection;

    public OnibusDAO() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Onibus onibus) {
        String sql = "INSERT INTO onibus(placa, ano, quilometragem, modelo) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, onibus.getPlaca());
            stmt.setInt(2, onibus.getAno());
            stmt.setFloat(3, onibus.getQuilometragem());
            stmt.setString(4, onibus.getModelo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OnibusDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Onibus onibus) {
        String sql = "UPDATE onibus SET ano=?, quilometragem=?, modelo=? WHERE placa=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, onibus.getAno());
            stmt.setFloat(2, onibus.getQuilometragem());
            stmt.setString(3, onibus.getModelo());
            stmt.setString(4, onibus.getPlaca());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OnibusDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Onibus onibus) {
        String sql = "DELETE FROM onibus WHERE placa=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, onibus.getPlaca());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OnibusDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Onibus> listar() {
        String sql = "SELECT * FROM onibus";
        List<Onibus> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Onibus onibus = new Onibus();
                onibus.setPlaca(resultado.getString("placa"));
                onibus.setAno(resultado.getInt("ano"));
                onibus.setQuilometragem(resultado.getFloat("quilometragem"));
                onibus.setModelo(resultado.getString("modelo"));
                retorno.add(onibus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OnibusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Onibus buscar(Onibus onibus) {
        String sql = "SELECT * FROM motorista WHERE placa=?";
        Onibus retorno = new Onibus();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, onibus.getPlaca());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                onibus.setPlaca(resultado.getString("placa"));
                onibus.setAno(resultado.getInt("ano"));
                onibus.setQuilometragem(resultado.getFloat("quilometragem"));
                onibus.setModelo(resultado.getString("modelo"));
                retorno = onibus;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OnibusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
