/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.LinhaDAO;
import com.sistema.model.pojo.Linha;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIDesktopCRUDLinhaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private WebView webView;
    @FXML
    private WebEngine webEngine;  
    @FXML
    private TableView<Linha> tableViewLinha;    
    @FXML 
    private TableColumn<Linha, Integer> tableColumnNumero;
    @FXML 
    private TableColumn<Linha, String> tableColumnNome;    
    @FXML 
    private TableColumn<Linha, String> tableColumnPonto;     
    private List<Linha> listLinha;
    private ObservableList<Linha> observableListLinha;
    private final LinhaDAO linhaDAO = new LinhaDAO();
    
    
    public void carregarTableView(){
        listLinha = linhaDAO.list();
        observableListLinha = FXCollections.observableArrayList(listLinha);
        tableColumnNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        //tableColumnPonto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPontoList().get(0).getLatitude())));
         
        tableViewLinha.setItems(observableListLinha);   

    }
    @Override    
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = webView.getEngine();
        webEngine.getLoadWorker().stateProperty()
        .addListener((obs, oldValue, newValue) -> {
          if (newValue == Worker.State.SUCCEEDED) {
            System.out.println("finished loading");
          }
        });
        //webEngine.load("http://html5test.com");
        webEngine.load(getClass().getResource("/mapa/mapaRota/googlemaps2.html").toString());
        carregarTableView();
    }
    
//    @FXML
//    public void handleReload(MouseEvent event) {
//        webEngine.load(getClass().getResource("/mapa/mapaRota/googlemaps2.html").toString());
//    }    

    
}
