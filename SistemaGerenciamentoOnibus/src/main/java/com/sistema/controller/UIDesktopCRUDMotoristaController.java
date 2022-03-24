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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private TextField textBuscaNome;
    
    @FXML 
    private TextField textCPF;
    @FXML 
    private TextField textRG;
    @FXML 
    private TextField textNome;
    @FXML 
    private TextField textTelefone;
    @FXML 
    private TextField textEndereco;    
    
    
    private List<Motorista> listMotorista;
    private ObservableList<Motorista> observableListMotorista;
    private final MotoristaDAO motoristaDAO = new MotoristaDAO();
    
    @FXML
    public void handleMouseAction(MouseEvent event){
        Motorista motorista = tableViewMotorista.getSelectionModel().getSelectedItem();
        textCPF.setText(motorista.getCpf());
        textRG.setText(motorista.getRg());
        textNome.setText(motorista.getNome());
        textTelefone.setText(motorista.getTelefone());
        textEndereco.setText(motorista.getEndereco());
    }   
    public void carregarTableView(){
        
        tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        
        listMotorista = motoristaDAO.list();
        //System.out.println(listMotorista);
        observableListMotorista = FXCollections.observableArrayList(listMotorista);
        tableViewMotorista.setItems(observableListMotorista);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableView();
    }    
    
}
