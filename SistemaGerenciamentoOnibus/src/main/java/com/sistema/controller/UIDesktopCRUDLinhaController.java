/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.LinhaDAO;
import com.sistema.model.dao.LinhaPontoDAO;
import com.sistema.model.dao.PontoDAO;
import com.sistema.model.pojo.Linha;
import com.sistema.model.pojo.LinhaPonto;
import com.sistema.model.pojo.Ponto;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
    // LinhaPonto
    @FXML
    private TableView<LinhaPonto> tableViewLinhaPonto;    
    @FXML 
    private TableColumn<LinhaPonto, String> tableColumnNumero;
    @FXML 
    private TableColumn<LinhaPonto, String> tableColumnNome;    
    @FXML 
    private TableColumn<LinhaPonto, String> tableColumnPonto;   
    @FXML 
    private TableColumn<LinhaPonto, String> tableColumnPontoLat;     
    @FXML 
    private TableColumn<LinhaPonto, String> tableColumnPontoLng;          
    @FXML 
    private ComboBox<Ponto> comboBoxPontos;
    // Ponto
    @FXML 
    private TableView<Ponto> tableViewPonto;    
    @FXML 
    private TableColumn<Ponto, String> tableColumnPonto2;   
    @FXML 
    private TableColumn<Ponto, String> tableColumnPontoLat2;     
    @FXML 
    private TableColumn<Ponto, String> tableColumnPontoLng2;   
    private int select;

    @FXML
    private TextField  textLatPonto, textLngPonto;
    @FXML
    private TextField textNomeLinha, textNumeroLinha;    
    
    @FXML
    private Button buttonAdicionar, buttonAlterar, buttonRemover;
    
    
    private List<LinhaPonto> listLinhaPonto;
    private ObservableList<LinhaPonto> observableListLinhaPonto;    
    private final LinhaPontoDAO linhaPontoDAO = new LinhaPontoDAO();
    private List<Linha> listLinha;
    private ObservableList<Linha> observableListLinha;
    private List<Ponto> listPonto;
    private ObservableList<Ponto> observableListPonto;    
    private final PontoDAO pontoDAO = new PontoDAO();    
    private final LinhaDAO linhaDAO = new LinhaDAO();
    
    
    public void carregarTablePontoLinhaView(){
        listLinhaPonto = linhaPontoDAO.list();
        observableListLinhaPonto = FXCollections.observableArrayList(listLinhaPonto);
        tableColumnNumero.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNumLinha().getNumero())));
        tableColumnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumLinha().getNome()));
        tableColumnPonto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdPonto().getId())));
        tableColumnPontoLat.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdPonto().getLatitude())));
        tableColumnPontoLng.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdPonto().getLongitude())));
        

        tableViewLinhaPonto.setItems(observableListLinhaPonto);   

    }
    
    @FXML
    public void handleMouseAction(MouseEvent event){
        
//        LinhaPonto linhaPonto = tableViewLinhaPonto.getSelectionModel().getSelectedItem().getNumLinha();
//        textNomeLinha.setText(linha.getNome());
//        textNumeroLinha.setText(linha.getNumero().toString());
//        System.out.println("ALOU");
//        listPonto =  new ArrayList();
//        listPonto  = linha.getPontoList();
//        observableListPonto = FXCollections.observableArrayList(listPonto);
//
//        tableViewPonto.setItems(observableListPonto);
        
        
//        listPonto = linhaPonto.getIdPonto();
//        observableListPonto = FXCollections.observableArrayList(listPonto);
//        tableViewPonto.set.setText(onibus.getQuilometragem().toString());
//        textModelo.setText(onibus.getModelo());
    }    
    @FXML
    public void handleAlterar(MouseEvent event){
        buttonAlterar.setStyle("-fx-background-color: #98f296");
        buttonAdicionar.setStyle(null);
        buttonRemover.setStyle(null);        
        setTextVazio();
        select = 1;
        textLatPonto.setEditable(true);
        textLngPonto.setEditable(true);
        textNomeLinha.setEditable(true);
        textNumeroLinha.setEditable(true);
    }
    @FXML
    public void handleDeletarPonto(MouseEvent event){
        Ponto selectedItem = tableViewPonto.getSelectionModel().getSelectedItem();
        tableViewPonto.getItems().remove(selectedItem);      
    }
    @FXML
    public void handleAdicionarPonto(MouseEvent event){
        Ponto selectedItem = comboBoxPontos.getSelectionModel().getSelectedItem();
        tableViewPonto.getItems().add(selectedItem);      
    }    
    @FXML
    public void handleBlockLinha(KeyEvent event){
        textNomeLinha.setText("");
        textNumeroLinha.setText("");
        textNomeLinha.setEditable(false);
        textNumeroLinha.setEditable(false);        
    }
    @FXML
    public void handleBlockPonto(KeyEvent event){
        textLatPonto.setText("");
        textLngPonto.setText("");
        textLatPonto.setEditable(false);   
        textLngPonto.setEditable(false);   
    }   
    @FXML
    public void handleAdicionar(MouseEvent event){
        buttonAlterar.setStyle(null);
        buttonAdicionar.setStyle("-fx-background-color: #98f296");
        buttonRemover.setStyle(null);        
        setTextVazio();
        select = 2;
        textLatPonto.setEditable(true);
        textLngPonto.setEditable(true);
        textNomeLinha.setEditable(true);
        textNumeroLinha.setEditable(true);
            
        
    }
    
    public void setTextVazio(){
        textLatPonto.setText("");
        textLngPonto.setText("");
        textNomeLinha.setText("");
        textNumeroLinha.setText("");
    }    
    @FXML 
    public void handleConfirmar(MouseEvent event){
        
        Ponto ponto;
        Linha linha;
        LinhaPonto linhaPonto ;
        switch(select){
            case 2:
                //Ponto
                if(textNomeLinha.getText().equals("")){

                    ponto = new Ponto();
                    ponto.setId(null);
                    ponto.setLatitude(Float.parseFloat(textLatPonto.getText()));
                    ponto.setLongitude(Float.parseFloat(textLngPonto.getText()));
                    pontoDAO.add(ponto);
                    carregarTablePontoView();
                    carregarComboBox();
                    tableColumnPonto2.getColumns().get(0).setVisible(false);
                    tableColumnPonto2.getColumns().get(0).setVisible(true);                    
                }   
                //Linha
                else{
                    linha = new Linha();
                    linha.setNome(textNomeLinha.getText());
                    linha.setNumero(Integer.parseInt(textNumeroLinha.getText()));
//                    List<Ponto> listPontoAux = new ArrayList<>();
//                    System.out.println("aaa");
//                    System.out.println(observableListPonto.size());
//                    for (int i = 0; i < observableListPonto.size(); i++){
//                        System.out.println(observableListPonto.get(i).toString());
//                        listPontoAux.add(observableListPonto.get(i));
//                    }
//                    linha.setPontoList(listPontoAux);
                    linhaDAO.add(linha);
                    
                    //LinhaPonto
                    
                    for (int i = 0; i < observableListPonto.size(); i++){
                        linhaPonto = new LinhaPonto();
                        linhaPonto.setId(null);
                        linhaPonto.setIdPonto(observableListPonto.get(i));
                        linhaPonto.setNumLinha(linha);
                        linhaPontoDAO.add(linhaPonto);

                    }
                    carregarTablePontoLinhaView();
                }
        }
        
    }
    public void carregarComboBox(){
        listPonto = pontoDAO.list();
        observableListPonto = FXCollections.observableArrayList(listPonto);
        comboBoxPontos.setItems(observableListPonto);
    }
    public void carregarTablePontoView(){
        listPonto = pontoDAO.list();
        observableListPonto = FXCollections.observableArrayList(listPonto);
        tableColumnPonto2.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnPontoLat2.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        tableColumnPontoLng2.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        

        tableViewPonto.setItems(observableListPonto);   

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
        carregarTablePontoLinhaView();
        carregarTablePontoView();
        carregarComboBox();
    }
    
//    @FXML
//    public void handleReload(MouseEvent event) {
//        webEngine.load(getClass().getResource("/mapa/mapaRota/googlemaps2.html").toString());
//    }    

    
}
