/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.bd.Database;
import com.sistema.model.bd.DatabaseFactory;
import com.sistema.model.dao.MotoristaDAO;
import com.sistema.model.dominio.Motorista;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author vini
 */
public class UITesteConexaoDBController implements Initializable {

    @FXML
    private TableView<Motorista> tableViewTesteBD;
    @FXML
    private TableColumn<Motorista, String> tableColumnPrimaria;
    @FXML
    private TableColumn<Motorista, String> tableColumnInfo;
    @FXML
    private Label labelInfo1;
    @FXML
    private Label labelInfo2;
    @FXML
    private Label labelInfo3;
    @FXML
    private Label labelInfo4;
    @FXML
    private Label labelInfo5;
    @FXML
    private Label labelInfo6;
    @FXML
    private Label labelInfo7;
    
    private List<Motorista> listObjetos;
    private ObservableList<Motorista> observableListObjetos;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final MotoristaDAO motoristaDAO = new MotoristaDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        motoristaDAO.setConnection(connection);
        carregarTableView();
    }    
    
    public void carregarTableView(){
        tableColumnPrimaria.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnInfo.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        listObjetos = motoristaDAO.listar();
        
        observableListObjetos = FXCollections.observableArrayList(listObjetos);
        tableViewTesteBD.setItems(observableListObjetos);
    }
}
