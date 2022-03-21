/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Linha;
import com.sistema.model.pojo.Ponto;
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
public class LinhaDAO {
    private Connection connection;

    public LinhaDAO() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Linha linha) {
        String sql = "INSERT INTO linha(numero, nome) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, linha.getNumero());
            stmt.setString(2, linha.getNome());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LinhaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Linha linha) {
        String sql = "UPDATE linha SET nome=? WHERE numero=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, linha.getNome());
            stmt.setInt(2, linha.getNumero());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LinhaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Linha linha) {
        String sql = "DELETE FROM linha WHERE numero=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, linha.getNumero());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LinhaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Linha> listar() {
        String sql = "SELECT * FROM linha";
        List<Linha> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Linha linha = new Linha();
                linha.setNumero(resultado.getInt("numero"));
                linha.setNome(resultado.getString("nome"));
                linha.setPontos(buscarPontos(linha));
                retorno.add(linha);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LinhaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Linha buscar(Linha linha) {
        String sql = "SELECT * FROM linha WHERE numero=?";
        Linha retorno = new Linha();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, linha.getNumero());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                linha.setNome(resultado.getString("nome"));
                linha.setPontos(buscarPontos(linha));
                retorno = linha;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LinhaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Ponto> buscarPontos(Linha linha) throws SQLException{
        List<Ponto> listaPontos = new ArrayList<>();
        // vvv PODE DAR PROBLEMAS vvv
        PontoDAO pontoDAO = new PontoDAO();
        
        pontoDAO.setConnection(connection);

        String sql = "SELECT * FROM linhaponto WHERE num_linha=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, linha.getNumero());
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            Ponto ponto = new Ponto();
            ponto.setId(resultado.getInt("id_ponto"));
            ponto = pontoDAO.buscar(ponto);
            listaPontos.add(ponto);
        }
        return listaPontos;
    }

}
