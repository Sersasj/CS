/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.bd.Database;
import com.sistema.model.bd.DatabaseFactory;
import com.sistema.model.dao.LinhaDAO;
import com.sistema.model.dao.OnibusDAO;
import com.sistema.model.dao.PontoDAO;
import com.sistema.model.pojo.Linha;
import com.sistema.model.pojo.Onibus;
import com.sistema.model.pojo.Ponto;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author vini
 */
public class UITesteConexaoDBController implements Initializable {
    
    /**
     * Initializes the controller class.
     * GERALMENTE O INITIALIZE ESTARIA MAIS EM BAIXO NO CODIGO
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TESTE BD 
        carregarTableView();
        
        // TESTE LINHAS
        inicializarComboBox();
        // quando uma linha e selecionada, a tableview e carregada com os pontos da linha
        comboBoxTesteLinha.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           carregarTableViewPontos(newValue);
        }); 
    }   
    
    // ############################### TESTE BD ##################################
    @FXML
    private TableView<Onibus> tableViewTesteBD;
    @FXML
    private TableColumn<Onibus, String> tableColumnPrimaria;
    @FXML
    private TableColumn<Onibus, String> tableColumnInfo;
    @FXML
    private Label labelInfo1;
    @FXML
    private Label labelInfo2;
    @FXML
    private Label labelInfo3;
    @FXML
    private Label labelInfo4;
    @FXML
    private Label labelInfo5;
    @FXML
    private Label labelInfo6;
    @FXML
    private Label labelInfo7;
    
    private List<Onibus> listObjetos;
    private ObservableList<Onibus> observableListObjetos;
    
    private final OnibusDAO testeDAO = new OnibusDAO(); 
    
    // carrega a tableview com os atributos definidos no codigo
    public void carregarTableView(){
        tableColumnPrimaria.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tableColumnInfo.setCellValueFactory(new PropertyValueFactory<>("ano"));
        listObjetos = testeDAO.list();
        
        observableListObjetos = FXCollections.observableArrayList(listObjetos);
        tableViewTesteBD.setItems(observableListObjetos);
    }
    
    
    // ############################### TESTE LINHAS ##################################
    
    @FXML
    private TableView<Ponto> tableViewTestePontos;
    @FXML
    private TableColumn<Linha, String> tableColumnIdPonto;
    @FXML
    private TableColumn<Linha, String> tableColumnLatPonto;
    @FXML
    private TableColumn<Linha, String> tableColumnLongPonto;
    @FXML
    private ComboBox<Linha> comboBoxTesteLinha;
    
    private List<Linha> listLinhas;
    private ObservableList<Linha> observableListLinhas;
    private final LinhaDAO linhaDAO = new LinhaDAO();
    
    // inicializa combobox para listar todas as linhas do BD
    public void inicializarComboBox(){
        // funcao para colocar so o toString da linha no combobox
        // ao inves do objeto linha
        comboBoxTesteLinha.setConverter(new StringConverter<Linha>() {
            @Override
            public String toString(Linha l) {
                if (l == null) {
                    return null;
                }else{
                    return l.toString();
                }
            }

            @Override
            public Linha fromString(String string) {
                return null;
            }
        });
        // busca as linhas no bd e coloca numa lista para o combobox utilizar
        listLinhas = linhaDAO.list();
        observableListLinhas = FXCollections.observableArrayList(listLinhas);
        comboBoxTesteLinha.setItems(observableListLinhas);
    }
    
    private List<Ponto> listPontos;
    private ObservableList<Ponto> observableListPontos;
    private final PontoDAO pontoDAO = new PontoDAO();
    
    // carrega a tableview com os pontos associados a uma linha
    public void carregarTableViewPontos(Linha linha){
        tableColumnIdPonto.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnLatPonto.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        tableColumnLongPonto.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        
        listPontos = new ArrayList<>();
        for(Ponto p: linha.getPontoList()){
            listPontos.add(pontoDAO.getById(p.getId()));
        }
        
        observableListPontos = FXCollections.observableArrayList(listPontos);
        tableViewTestePontos.setItems(observableListPontos);
    }
    
}
