/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.FuncionarioDAO;
import com.sistema.model.dao.MotoristaDAO;
import com.sistema.model.pojo.Funcionario;
import com.sistema.model.pojo.Motorista;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIDesktopCRUDMotoristaController implements Initializable {

    /**
     * Initializes the controller class.
     */  
    @FXML
    private TableView<Motorista> tableViewMotorista;
    @FXML
    private TableColumn<Motorista, String> tableColumnCNH;    
    @FXML
    private TableColumn<Motorista, String> tableColumnCPF;
    @FXML
    private TableColumn<Motorista, String> tableColumnRG;
    @FXML
    private TableColumn<Motorista, String> tableColumnNome;
    @FXML
    private TableColumn<Motorista, String> tableColumnTelefone;
    @FXML
    private TableColumn<Motorista, String> tableColumnEndereco;
    @FXML
    private TextField textBuscaNome;
    private int select;
    @FXML
    private TextField textCNH;
    @FXML 
    private TextField textCPF;
    @FXML 
    private TextField textRG;
    @FXML 
    private TextField textNome;
    @FXML 
    private TextField textTelefone;
    @FXML 
    private TextField textEndereco;    
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonAdicionar;
    
    private List<Motorista> listMotorista;
    private ObservableList<Motorista> observableListMotorista;
    private final MotoristaDAO motoristaDAO = new MotoristaDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    
     
    @FXML
    public void handleMouseAction(MouseEvent event){
        
        Motorista motorista = tableViewMotorista.getSelectionModel().getSelectedItem();
        textCNH.setText(motorista.getCnh());
        textCPF.setText(motorista.getCpf());
        textRG.setText(motorista.getRg());
        textNome.setText(motorista.getNome());
        textTelefone.setText(motorista.getTelefone());
        textEndereco.setText(motorista.getEndereco());
    }
    @FXML 
    public void handleConfirmar(MouseEvent event){
        Motorista motorista;
        switch(select){
            case 1:
                motorista = tableViewMotorista.getSelectionModel().getSelectedItem();
                motorista.setCnh(textCNH.getText());
                motorista.setNome(textNome.getText());
                motorista.setTelefone(textTelefone.getText());
                motorista.setEndereco(textEndereco.getText());
                // alterar cpf quebra tudo
                //motorista.setCpf(textCPF.getText());
                motorista.setRg(textRG.getText());
                motoristaDAO.update(motorista);
        
                carregarTableView();
                tableViewMotorista.getColumns().get(0).setVisible(false);
                tableViewMotorista.getColumns().get(0).setVisible(true);
                break;
            case 2:
                motorista = new Motorista();
                motorista.setCnh(textCNH.getText());
                motorista.setCpf(textCPF.getText());
                motorista.setNome(textNome.getText());
                motorista.setTelefone(textTelefone.getText());
                motorista.setEndereco(textEndereco.getText()); 
                motorista.setRg(textRG.getText());
        
  
                motoristaDAO.add(motorista);
                carregarTableView();
                tableViewMotorista.getColumns().get(0).setVisible(false);
                tableViewMotorista.getColumns().get(0).setVisible(true);
                break;
                
            case 3:
                //motorista = motoristaDAO.getById(textCPF.getText());
                //motoristaDAO.delete(textCPF.getText());
                motoristaDAO.remove(textCPF.getText());
                carregarTableView();
                tableViewMotorista.getColumns().get(0).setVisible(false);
                tableViewMotorista.getColumns().get(0).setVisible(true);  
                break;
            default:
                System.out.println("error");
        }
       
        

            
    }
    public void setTextVazio(){
        textCNH.setText("");
        textCPF.setText("");
        textNome.setText("");
        textTelefone.setText("");
        textEndereco.setText("");
        textRG.setText("");
    }
    @FXML
    public void handleCancelar(MouseEvent event){
        buttonAlterar.setStyle(null);
        buttonAdicionar.setStyle(null);
        buttonRemover.setStyle(null);
        select = 0;
    }
    @FXML
    public void handleRemoverMotorista(MouseEvent event){
        buttonAlterar.setStyle(null);
        buttonAdicionar.setStyle(null);
        buttonRemover.setStyle("-fx-background-color: MediumSeaGreen");
        select = 3;
        
    }
    @FXML
    public void handleAdicionarMotorista(MouseEvent event){
        buttonAlterar.setStyle(null);
        buttonAdicionar.setStyle("-fx-background-color: MediumSeaGreen");
        buttonRemover.setStyle(null);        
        setTextVazio();
        select = 2;
        textCNH.setEditable(true);
        textCPF.setEditable(true);
        textNome.setEditable(true);
        textTelefone.setEditable(true);
        textEndereco.setEditable(true);
        textRG.setEditable(true);
       
        
    }
    @FXML    
    public void handleAlterarMotorista(MouseEvent eventt){
        
        buttonAlterar.setStyle("-fx-background-color: MediumSeaGreen");
        buttonAdicionar.setStyle(null);
        buttonRemover.setStyle(null);

        select = 1;

        textCNH.setEditable(true);
        textCPF.setEditable(false);
        textNome.setEditable(true);
        textTelefone.setEditable(true);
        textEndereco.setEditable(true);
        textRG.setEditable(true);
    }
    
    public void carregarTableView(){
        
        listMotorista = motoristaDAO.list();
        observableListMotorista = FXCollections.observableArrayList(listMotorista);
       
        tableColumnCNH.setCellValueFactory(new PropertyValueFactory<>("cnh"));
        tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableViewMotorista.setItems(observableListMotorista);   

    }
    
    
    public void search(){
        
    FilteredList<Motorista> filteredData = new FilteredList<>(observableListMotorista, b -> true);
    textBuscaNome.textProperty().addListener ((observable, oldValue, newValue) -> {
        filteredData.setPredicate(motorista -> {
            if (newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (motorista.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }            
            return false;
        });
    });
    
 
    SortedList<Motorista> sortedData = new SortedList<>(filteredData);
    
    sortedData.comparatorProperty().bind(tableViewMotorista.comparatorProperty());
    tableViewMotorista.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
    
        carregarTableView();
        search();
        
    }    
    
}