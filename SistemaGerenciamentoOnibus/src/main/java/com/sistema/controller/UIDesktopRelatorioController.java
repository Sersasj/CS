/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.CorridaDAO;
import com.sistema.model.dao.LinhaDAO;
import com.sistema.model.pojo.Corrida;
import com.sistema.model.pojo.Linha;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private LineChart lineChart;
    @FXML
    private TableColumn<Corrida, String> tableColumnID, tableColumnMotorista, tableColumnOnibus, tableColumnLinha;
    @FXML
    private TableColumn<Corrida, String> tableColumnInicio, tableColumnFim, tableColumnPagantes, tableColumnNaoPagantes;
    @FXML
    private TableColumn<Corrida, String> tableColumnDistancia, tableColumnConsumo;  
    @FXML
    private DatePicker datePickerInicio, datePickerFim;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private BarChart barChart;
    @FXML
    private ComboBox<Linha> comboBoxLinhas;    
    private Date dataInicial = null, dataFinal = null;
    private List<Corrida> listCorrida;
    private ObservableList<Corrida> observableListCorrida;
    private final CorridaDAO corridaDAO = new CorridaDAO();
    private final LinhaDAO linhaDAO = new LinhaDAO();
    private List<Linha> listLinhas;
    private ObservableList<Linha> observableListLinhas;
    
 
    

    

    public void handleDatePickerInicio(ActionEvent e) {
        LocalDate localDateInicial = datePickerInicio.getValue();
        if (localDateInicial != null) {
            localDateInicial = localDateInicial.withDayOfMonth(1);
            datePickerInicio.setValue(localDateInicial);
            dataInicial = Date.from(localDateInicial.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());            
        }
    }

    public void handleDatePickerFim(ActionEvent e) {
        LocalDate localDateFinal = datePickerFim.getValue();
        if (localDateFinal != null) {
            localDateFinal = localDateFinal.withDayOfMonth(localDateFinal.getMonth().length(localDateFinal.isLeapYear()));
            datePickerFim.setValue(localDateFinal);
            dataFinal = Date.from(localDateFinal.atTime(23, 59, 59, 999999999).atZone(ZoneId.systemDefault()).toInstant());
        }
    }
    public void carregarComboBoxLinhas() {
        listLinhas = linhaDAO.list();
        observableListLinhas = FXCollections.observableArrayList(listLinhas);
        comboBoxLinhas.setItems(observableListLinhas);
    }

    @FXML
    public void handleComboBox(MouseEvent event) {
        lucroChart();
        passageiroChart();
    }
    public void lucroChart(){
        List<Object[]> resultado;

        barChart.getData().clear();
        XYChart.Series seriesGasto = new XYChart.Series();
        XYChart.Series seriesReceita = new XYChart.Series();
        XYChart.Series seriesLucro = new XYChart.Series();
        seriesGasto.setName("Gasto");
        seriesReceita.setName("Receita");
        seriesLucro.setName("Lucro");
       // Linha
        if(toggleButton.isSelected()){
            resultado = corridaDAO.listLucroLinhaMensal(dataInicial, dataFinal, comboBoxLinhas.getValue());
            
        }
        // Geral
        else{
            resultado = corridaDAO.listLucroMensal(dataInicial, dataFinal);

        }        
        for (int i = 0; i < resultado.size(); i++){
            List<Object> resultadoMes = Arrays.asList(resultado.get(i));
            System.out.println(resultadoMes);
            
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
    
    public void passageiroChart(){
        lineChart.getData().clear();
        XYChart.Series quantidadePassageiros = new XYChart.Series();
        quantidadePassageiros.setName("Passageiros");
        List<Object[]> resultado;
        // Linha
        if(toggleButton.isSelected()){
            resultado = corridaDAO.listPassageiroLinhaMensal(dataInicial, dataFinal, comboBoxLinhas.getValue());

            System.out.println("adadawdawda");
        }
        // Geral
        else{
            resultado = corridaDAO.listPassageiroMensal(dataInicial, dataFinal);

        }
        
        for (int i = 0; i < resultado.size(); i++){
            List<Object> passageirosMes = Arrays.asList(resultado.get(i));
            System.out.println(passageirosMes);
            
            String data = passageirosMes.get(0).toString() + "/" +  passageirosMes.get(1).toString();
            Float passageiro = Float.parseFloat(passageirosMes.get(2).toString());
                    
            quantidadePassageiros.getData().add(new XYChart.Data(data,passageiro));

           
        }
        lineChart.getData().addAll(quantidadePassageiros);

    }

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboBoxLinhas() ;
        
        barChart.setAnimated(false);
        //lucroChart();
    }

}
