/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.bd.Database;
import com.sistema.model.bd.DatabaseFactory;
import com.sistema.model.dao.MotoristaDAO;
import com.sistema.model.pojo.Motorista;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIDesktopCRUDMotoristaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();    
    @FXML
    private TableView<Motorista> tableViewMotorista;
    @FXML
    private TableColumn<Motorista, String> tableColumnCPF;
    @FXML
    private TableColumn<Motorista, String> tableColumnRG;
    @FXML
    private TableColumn<Motorista, String> tableColumnNome;
    @FXML
    private TableColumn<Motorista, String> tableColumnTelefone;
    @FXML
    private TableColumn<Motorista, String> tableColumnEndereco;
   
    private List<Motorista> listMotorista;
    private ObservableList<Motorista> observableListMotorista;
    private final MotoristaDAO motoristaDAO = new MotoristaDAO();
    
    public void carregarTableView(){
        
        tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        //tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        //endereço quebra tudo
        
        listMotorista = motoristaDAO.listar();
        System.out.println(listMotorista);
        observableListMotorista = FXCollections.observableArrayList(listMotorista);
        tableViewMotorista.setItems(observableListMotorista);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        motoristaDAO.setConnection(connection);
        carregarTableView();
    }    
    
}
