/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.CorridaDAO;
import com.sistema.model.pojo.Corrida;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
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
public class UIDesktopRelatorioController implements Initializable {

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
    @FXML
    private BarChart barChart;
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
        lucroChart();

        
    }
    

    public void handleDatePickerInicio(ActionEvent e) {
        LocalDate localDateInicial = datePickerInicio.getValue();
        if (localDateInicial != null) {
            localDateInicial = localDateInicial.withDayOfMonth(1);
            datePickerInicio.setValue(localDateInicial);
            dataInicial = Date.from(localDateInicial.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());            
            atualizarTableView();
        }
    }

    public void handleDatePickerFim(ActionEvent e) {
        LocalDate localDateFinal = datePickerFim.getValue();
        if (localDateFinal != null) {
            localDateFinal = localDateFinal.withDayOfMonth(localDateFinal.getMonth().length(localDateFinal.isLeapYear()));
            datePickerFim.setValue(localDateFinal);
            dataFinal = Date.from(localDateFinal.atTime(23, 59, 59, 999999999).atZone(ZoneId.systemDefault()).toInstant());
            atualizarTableView();
        }
    }

    
    public void lucroChart(){
        barChart.getData().clear();
        XYChart.Series seriesGasto = new XYChart.Series();
        XYChart.Series seriesReceita = new XYChart.Series();
        XYChart.Series seriesLucro = new XYChart.Series();
        seriesGasto.setName("Gasto");
        seriesReceita.setName("Receita");
        seriesLucro.setName("Lucro");        
        List<Object[]> resultado = corridaDAO.listLucroMensal(dataInicial, dataFinal);
        for (int i = 0; i < resultado.size(); i++){
            List<Object> resultadoMes = Arrays.asList(resultado.get(i));
            
            
            String data = resultadoMes.get(0).toString() + "/" +  resultadoMes.get(1).toString();
            Float passagem = Float.parseFloat(resultadoMes.get(2).toString());
            Float consumo = Float.parseFloat(resultadoMes.get(3).toString());

            
            seriesGasto.getData().add(new XYChart.Data(data,consumo*5.0));
            seriesReceita.getData().add(new XYChart.Data(data,passagem*4.0));
            seriesLucro.getData().add(new XYChart.Data(data,passagem*4.0 - consumo*5.0));

           
        }        
                

        barChart.getData().addAll(seriesGasto);
        barChart.getData().addAll(seriesReceita);
        barChart.getData().addAll(seriesLucro);

        
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
        barChart.setAnimated(false);

        carregarTableView();
        search();
        lucroChart();
    }

}
