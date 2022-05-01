/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.OnibusDAO;
import com.sistema.model.pojo.Motorista;
import com.sistema.model.pojo.Onibus;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class UIDesktopCRUDOnibusController implements Initializable {

    /**
     * Initializes the controller class.
     */  
    @FXML
    private TableView<Onibus> tableViewOnibus;
    @FXML
    private TableColumn<Onibus, String> tableColumnPlaca;    
    @FXML
    private TableColumn<Onibus, Integer> tableColumnAno;
    @FXML
    private TableColumn<Onibus, Integer> tableColumnQuilometragem;
    @FXML
    private TableColumn<Onibus, String> tableColumnModelo;
    @FXML
    private TextField textBusca;
    private int select;
    @FXML
    private TextField textPlaca;
    @FXML 
    private TextField textAno;
    @FXML 
    private TextField textQuilometragem;
    @FXML 
    private TextField textModelo;
    @FXML     
    private Button buttonAlterar;
    @FXML     
    private Button buttonAdicionar;
    @FXML     
    private Button buttonRemover;
    
    private List<Onibus> listOnibus;
    private ObservableList<Onibus> observableListOnibus;
    private final OnibusDAO onibusDAO = new OnibusDAO();
    
    
     
    @FXML
    public void handleMouseAction(MouseEvent event){
        
        Onibus onibus = tableViewOnibus.getSelectionModel().getSelectedItem();
        textPlaca.setText(onibus.getPlaca());
        textAno.setText(onibus.getAno().toString());
        textQuilometragem.setText(onibus.getQuilometragem().toString());
        textModelo.setText(onibus.getModelo());
    }
    @FXML 
    public void handleConfirmar(MouseEvent event){
        Onibus onibus;
        switch(select){
            case 1:
                onibus = tableViewOnibus.getSelectionModel().getSelectedItem();
                onibus.setPlaca(textPlaca.getText());
                onibus.setAno(Integer.parseInt(textAno.getText()));
                onibus.setQuilometragem(Float.parseFloat(textQuilometragem.getText()));
                onibus.setModelo(textModelo.getText());
                onibusDAO.update(onibus);
        
                carregarTableView();
                tableViewOnibus.getColumns().get(0).setVisible(false);
                tableViewOnibus.getColumns().get(0).setVisible(true);
                break;
            case 2:
                onibus = new Onibus();
                onibus.setPlaca(textPlaca.getText());
                onibus.setAno(Integer.parseInt(textAno.getText()));
                onibus.setQuilometragem(Float.parseFloat(textQuilometragem.getText()));
                onibus.setModelo(textModelo.getText());        
  
                onibusDAO.add(onibus);
                carregarTableView();
                tableViewOnibus.getColumns().get(0).setVisible(false);
                tableViewOnibus.getColumns().get(0).setVisible(true);
                break;
                
            case 3:
                onibusDAO.remove(textPlaca.getText());
                carregarTableView();
                tableViewOnibus.getColumns().get(0).setVisible(false);
                tableViewOnibus.getColumns().get(0).setVisible(true);  
                break;
            default:
                System.out.println("error");
        }
       
        

            
    }
    public void setTextVazio(){
        textPlaca.setText("");
        textAno.setText("");
        textQuilometragem.setText("");
        textModelo.setText("");
    }
    @FXML
    public void handleCancelar(MouseEvent event){
        buttonAlterar.setStyle(null);
        buttonAdicionar.setStyle(null);
        buttonRemover.setStyle(null);        
        select = 0;
    }
    public void handleRemoverOnibus(MouseEvent event){
        buttonAlterar.setStyle(null);
        buttonAdicionar.setStyle(null);
        buttonRemover.setStyle("-fx-background-color: #f29696");     
        select = 3;
        
    }
    @FXML
    public void handleAdicionarOnibus(MouseEvent event){
        buttonAlterar.setStyle(null);
        buttonAdicionar.setStyle("-fx-background-color: #98f296");
        buttonRemover.setStyle(null);     
        setTextVazio();
        select = 2;
        textPlaca.setEditable(true);
        textAno.setEditable(true);
        textQuilometragem.setEditable(true);
        textModelo.setEditable(true);       
        
    }
    @FXML
    public void handleAlterarOnibus(MouseEvent eventt){
        buttonAlterar.setStyle("-fx-background-color: #f2ef96");
        buttonAdicionar.setStyle(null);
        buttonRemover.setStyle(null);
        select = 1;

        textPlaca.setEditable(false);
        textAno.setEditable(false);
        textQuilometragem.setEditable(true);
        textModelo.setEditable(true);
    }
    
    public void carregarTableView(){
        
        listOnibus = onibusDAO.list();
        observableListOnibus = FXCollections.observableArrayList(listOnibus);
       
        tableColumnPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tableColumnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tableColumnQuilometragem.setCellValueFactory(new PropertyValueFactory<>("quilometragem"));
        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));   
        tableViewOnibus.setItems(observableListOnibus);   
        
    }
    
    public void search(){
        
    FilteredList<Onibus> filteredData = new FilteredList<>(observableListOnibus, b -> true);
    textBusca.textProperty().addListener ((observable, oldValue, newValue) -> {
        filteredData.setPredicate(onibus -> {
            if (newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (onibus.getPlaca().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }
            if (onibus.getAno().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }
            if (onibus.getModelo().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }            
            return false;
        });
    });
    
 
    SortedList<Onibus> sortedData = new SortedList<>(filteredData);
    
    sortedData.comparatorProperty().bind(tableViewOnibus.comparatorProperty());
    tableViewOnibus.setItems(sortedData);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableView();
        search();
    }    
    
}
