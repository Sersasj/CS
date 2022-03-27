/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.OnibusDAO;
import com.sistema.model.pojo.Onibus;
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
    private TextField textBuscaPlaca;
    private int select;
    @FXML
    private TextField textPlaca;
    @FXML 
    private Integer intAno;
    @FXML 
    private Integer intQuilometragem;
    @FXML 
    private TextField textModelo;
    @FXML   
    
    
    private List<Onibus> listOnibus;
    private ObservableList<Onibus> observableListOnibus;
    private final OnibusDAO onibusDAO = new OnibusDAO();
    
    
     
    @FXML
    public void handleMouseAction(MouseEvent event){
        
        Onibus onibus = tableViewOnibus.getSelectionModel().getSelectedItem();
        textPlaca.setText(onibus.getPlaca());
        intAno.setInteger(onibus.getAno());
        intQuilometragem.setInt(onibus.getQuilometragem());
        textModelo.setText(onibus.getModelo());
    }
    @FXML 
    public void handleConfirmar(MouseEvent event){
        Onibus onibus;
        switch(select){
            case 1:
                onibus = tableViewOnibus.getSelectionModel().getSelectedItem();
                onibus.setPlaca(textPlaca.getText());
                onibus.setAno(intAno.getInt());
                onibus.setQuilometragem(intQuilometragem.getInt());
                onibus.setModelo(textModelo.getText());
                onibusDAO.update(onibus);
        
                carregarTableView();
                tableViewOnibus.getColumns().get(0).setVisible(false);
                tableViewOnibus.getColumns().get(0).setVisible(true);
                break;
            case 2:
                onibus = new Onibus();
                onibus.setPlaca(textPlaca.getText());
                onibus.setAno(intAno.getInteger());
                onibus.setQuilometragem(intQuilometragem.getInteger());
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
        intAno.setInteger("");
        intQuilometragem.setInteger("");
        textModelo.setText("");
    }
    @FXML
    public void handleCancelar(MouseEvent event){
        select = 0;
    }
    public void handleRemoverMotorista(MouseEvent event){
        select = 3;
        
    }
    @FXML
    public void handleAdicionarOnibus(MouseEvent event){
        setTextVazio();
        select = 2;
        textPlaca.setEditable(true);
        intAno.setEditable(true);
        intQuilometragem.setEditable(true);
        textModelo.setEditable(true);       
        
    }
    @FXML
    public void handleAlterarOnibus(MouseEvent eventt){
        select = 1;

        textPlaca.setEditable(true);
        intAno.setEditable(false);
        intQuilometragem.setEditable(true);
        textModelo.setEditable(true);
    }
    
    public void carregarTableView(){
        
        listOnibus = onibusDAO.list();
        observableListOnibus = FXCollections.observableArrayList(listOnibus);
       
        tableColumnPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tableColumnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tableColumnQuilometragem.setCellValueFactory(new PropertyValueFactory<>("quilometragem"));
        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));   

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableView();
    }    
    
}
