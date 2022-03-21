/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Ponto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vini
 */
public class PontoDAO {
    private Connection connection;

    public PontoDAO() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Ponto ponto) {
        String sql = "INSERT INTO ponto(id_ponto, latitude, longitude) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setNull(1, Types.INTEGER);
            stmt.setFloat(2, ponto.getLatitude());
            stmt.setFloat(3, ponto.getLongitude());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PontoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Ponto ponto) {
        String sql = "UPDATE ponto SET latitude=?, longitude=? WHERE id_ponto=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setFloat(1, ponto.getLatitude());
            stmt.setFloat(2, ponto.getLongitude());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PontoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Ponto ponto) {
        String sql = "DELETE FROM ponto WHERE id_ponto=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, ponto.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PontoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Ponto> listar() {
        String sql = "SELECT * FROM ponto";
        List<Ponto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Ponto ponto = new Ponto();
                ponto.setId(resultado.getInt("id_ponto"));
                ponto.setLatitude(resultado.getFloat("latitude"));
                ponto.setLongitude(resultado.getFloat("longitude"));
                retorno.add(ponto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Ponto buscar(Ponto ponto) {
        String sql = "SELECT * FROM ponto WHERE id_ponto=?";
        Ponto retorno = new Ponto();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, ponto.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                ponto.setId(resultado.getInt("id_ponto"));
                ponto.setLatitude(resultado.getFloat("latitude"));
                ponto.setLongitude(resultado.getFloat("longitude"));
                retorno = ponto;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
