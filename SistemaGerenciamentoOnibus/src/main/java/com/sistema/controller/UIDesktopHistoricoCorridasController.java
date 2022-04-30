/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.CorridaDAO;
import com.sistema.model.pojo.Corrida;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
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
    @FXML
    private TableColumn<Corrida, String> tableColumnDistancia, tableColumnConsumo;    
    @FXML
    private DatePicker datePickerInicio, datePickerFim;
    @FXML
    private TextField textBusca;  

    private Date dataInicial = null, dataFinal = null;
    private List<Corrida> listCorrida;
    private ObservableList<Corrida> observableListCorrida;
    private final CorridaDAO corridaDAO = new CorridaDAO();

    public void carregarTableView() {

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
        tableColumnDistancia.setCellValueFactory(new PropertyValueFactory<>("distanciaPercorrida"));
        tableColumnConsumo.setCellValueFactory(new PropertyValueFactory<>("consumoCombustivel"));
        tableViewCorrida.setItems(observableListCorrida);
    }
    
    public void atualizarTableView() {

        listCorrida = corridaDAO.list(dataInicial, dataFinal);
        observableListCorrida = FXCollections.observableArrayList(listCorrida);

        tableViewCorrida.setItems(observableListCorrida);
    }

    public void handleDatePickerInicio(ActionEvent e) {
        LocalDate localDateInicial = datePickerInicio.getValue();
        if (localDateInicial != null) {
            dataInicial = Date.from(localDateInicial.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            atualizarTableView();
        }
    }

    public void handleDatePickerFim(ActionEvent e) {
        LocalDate localDateFinal = datePickerFim.getValue();
        if (localDateFinal != null) {
            dataFinal = Date.from(localDateFinal.atTime(23, 59, 59, 999999999).atZone(ZoneId.systemDefault()).toInstant());
            atualizarTableView();
        }
    }

    public void search(){
        
    FilteredList<Corrida> filteredData = new FilteredList<>(observableListCorrida, b -> true);
    textBusca.textProperty().addListener ((observable, oldValue, newValue) -> {
        filteredData.setPredicate(corrida -> {
            if (newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (corrida.getOnibus().getPlaca().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }
            if (corrida.getLinha().getNome().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }
            if (corrida.getLinha().getNumero().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }            
            if (corrida.getMotorista().getNome().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }            
            return false;
        });
    });
    
 
    SortedList<Corrida> sortedData = new SortedList<>(filteredData);
    
    sortedData.comparatorProperty().bind(tableViewCorrida.comparatorProperty());
    tableViewCorrida.setItems(sortedData);
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableView();
        search();
    }

}
