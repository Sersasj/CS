/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.CorridaDAO;
import com.sistema.model.pojo.Corrida;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIDesktopHistoricoCorridasController implements Initializable {

    /**
     * Initializes the controller class.
     */  
    @FXML
    private TableView<Corrida> tableViewCorrida;
    @FXML
    private TableColumn<Corrida, String> tableColumnID, tableColumnMotorista, tableColumnOnibus, tableColumnLinha;
    @FXML
    private TableColumn<Corrida, String> tableColumnInicio, tableColumnFim, tableColumnPagantes, tableColumnNaoPagantes;    
//    @FXML
//    private TextField textBuscaNome;  
    
    private List<Corrida> listCorrida;
    private ObservableList<Corrida> observableListCorrida;
    private final CorridaDAO corridaDAO = new CorridaDAO();
    
    
    public void carregarTableView(){
        
        listCorrida = corridaDAO.list();
        observableListCorrida = FXCollections.observableArrayList(listCorrida);
       
        tableColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnMotorista.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMotorista().getNome()));
        tableColumnOnibus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOnibus().getPlaca()));
        tableColumnLinha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLinha().toString()));
        tableColumnInicio.setCellValueFactory(new PropertyValueFactory<>("inicioCorrida"));
        tableColumnFim.setCellValueFactory(new PropertyValueFactory<>("fimCorrida"));
        tableColumnPagantes.setCellValueFactory(new PropertyValueFactory<>("passPagantes"));
        tableColumnNaoPagantes.setCellValueFactory(new PropertyValueFactory<>("passNaoPagantes"));
       
        tableViewCorrida.setItems(observableListCorrida);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableView();
    }    
    
}