/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.ProblemaDAO;
import com.sistema.model.pojo.Onibus;
import com.sistema.model.pojo.Problema;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
public class UIDesktopProblemasController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TableView<Problema> tableViewProblema;
    @FXML
    private TableColumn<Problema, String> tableColumnPlaca;    
    @FXML
    private TableColumn<Problema, String> tableColumnAno;
    @FXML
    private TableColumn<Problema, String> tableColumnTipo;
    @FXML
    private TableColumn<Problema, String> tableColumnModelo;
    @FXML  
    private TableColumn<Problema, String> tableColumnData;

    @FXML
    private TextField textBusca, textAno, textPlaca, textQuilometragem, textData, textDescricao, textModelo;
    
    private List<Problema> listProblema;
    private ObservableList<Problema> observableListProblema;
    private final ProblemaDAO problemaDAO = new ProblemaDAO();
        
    public void carregarTableView(){
        
        listProblema = problemaDAO.list();
        observableListProblema = FXCollections.observableArrayList(listProblema);
        System.out.println(observableListProblema);
        tableColumnPlaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOnibus().getPlaca()));
        tableColumnAno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOnibus().getAno().toString()));
        tableColumnModelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOnibus().getModelo()));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("dataHorario"));
        tableColumnTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdTipo().getTipo()));
        tableViewProblema.setItems(observableListProblema);   
        
    }    
    @FXML
    public void handleMouseAction(MouseEvent event){
        
        Problema problema = tableViewProblema.getSelectionModel().getSelectedItem();
        System.out.println(problema.getDescricao());
        textDescricao.setText(problema.getDescricao());
        textAno.setText(problema.getOnibus().getAno().toString());
        textQuilometragem.setText(problema.getOnibus().getQuilometragem().toString());
        textModelo.setText(problema.getOnibus().getModelo());
        textPlaca.setText(problema.getOnibus().getPlaca());
        textData.setText(problema.getDataHorario().toString());
    }    
    public void search(){
    FilteredList<Problema> filteredData = new FilteredList<>(observableListProblema, b -> true);
    textBusca.textProperty().addListener ((observable, oldValue, newValue) -> {
        filteredData.setPredicate(problema -> {
            if (newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (problema.getOnibus().getPlaca().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }
            if (problema.getOnibus().getAno().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }
            if (problema.getOnibus().getModelo().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }      
            if (problema.getIdTipo().getTipo().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }    
            return false;
        });
    });
    
    SortedList<Problema> sortedData = new SortedList<>(filteredData);
    
    sortedData.comparatorProperty().bind(tableViewProblema.comparatorProperty());
    tableViewProblema.setItems(sortedData);    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableView();
        search();
        // TODO
    }    
    
}
