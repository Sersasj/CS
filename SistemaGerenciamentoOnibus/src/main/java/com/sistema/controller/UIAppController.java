/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.bd.Database;
import com.sistema.model.bd.DatabaseFactory;
import com.sistema.model.dao.LinhaDAO;
import com.sistema.model.dominio.Linha;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIAppController implements Initializable {
    
    
    @FXML
    private ComboBox<Linha> linhasOnibus;
    @FXML
    private TextField placaOnibus;
    @FXML
    private TextField numeroCpf;
    @FXML 
    private Button botaoConfirmar;
    
    private List<Linha> listLinhas;
    private ObservableList<Linha> observableListLinhas;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();    
    private final LinhaDAO linhaDAO = new LinhaDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        linhaDAO.setConnection(connection);
        carregarLinhaView();
    }

    public void carregarLinhaView(){
        listLinhas = linhaDAO.listar();
        observableListLinhas = FXCollections.observableArrayList(listLinhas);
        linhasOnibus.setItems(observableListLinhas);
        //mostrar nome
    }
    
}
