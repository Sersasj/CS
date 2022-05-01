/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.AdministradorDAO;
import com.sistema.model.pojo.Administrador;
import java.net.URL;
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


    
public class UIDesktopCRUDAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Administrador> tableViewAdmin;
    @FXML
    private TableColumn<Administrador, String> tableColumnUsuario;  
    @FXML
    private TableColumn<Administrador, String> tableColumnSenha;      
    @FXML
    private TableColumn<Administrador, String> tableColumnCPF;
    @FXML
    private TableColumn<Administrador, String> tableColumnRG;
    @FXML
    private TableColumn<Administrador, String> tableColumnNome;
    @FXML
    private TableColumn<Administrador, String> tableColumnTelefone;
    @FXML
    private TableColumn<Administrador, String> tableColumnEndereco;    
    @FXML
    private TextField textBuscaNome;
    private int select;
    @FXML
    private TextField textUsuario;
    @FXML 
    private TextField textSenha;           
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

    private List<Administrador> listAdmin;
    private ObservableList<Administrador> observableListAdmin;
    private final AdministradorDAO adminDAO = new AdministradorDAO();

    @FXML 
    public void handleConfirmar(MouseEvent event){
        Administrador admin;
        switch(select){
            case 1:
                admin = tableViewAdmin.getSelectionModel().getSelectedItem();
                admin.setNome(textNome.getText());
                admin.setTelefone(textTelefone.getText());
                admin.setEndereco(textEndereco.getText());
                // alterar cpf quebra tudo
                //motorista.setCpf(textCPF.getText());
                admin.setRg(textRG.getText());
                admin.setUsername(textUsuario.getText());
                admin.setSenha(textSenha.getText());

                adminDAO.update(admin);
        
                carregarTableView();
                tableViewAdmin.getColumns().get(0).setVisible(false);
                tableViewAdmin.getColumns().get(0).setVisible(true);
                break;
            case 2:
                admin = new Administrador();
                admin.setCpf(textCPF.getText());
                admin.setNome(textNome.getText());
                admin.setTelefone(textTelefone.getText());
                admin.setEndereco(textEndereco.getText()); 
                admin.setRg(textRG.getText());
                admin.setUsername(textUsuario.getText());
                admin.setSenha(textSenha.getText());        
  
                adminDAO.add(admin);
                carregarTableView();
                tableViewAdmin.getColumns().get(0).setVisible(false);
                tableViewAdmin.getColumns().get(0).setVisible(true);
                break;
                
            case 3:
                //admin = adminDAO.getById(textCPF.getText());
                //motoristaDAO.delete(textCPF.getText());
                adminDAO.remove(textCPF.getText());
                carregarTableView();
                tableViewAdmin.getColumns().get(0).setVisible(false);
                tableViewAdmin.getColumns().get(0).setVisible(true);  
                break;
            default:
                System.out.println("error");
        }
    } 
   @FXML
    public void handleMouseAction(MouseEvent event){
        
        Administrador admin = tableViewAdmin.getSelectionModel().getSelectedItem();
        textCPF.setText(admin.getCpf());
        textRG.setText(admin.getRg());
        textNome.setText(admin.getNome());
        textTelefone.setText(admin.getTelefone());
        textEndereco.setText(admin.getEndereco());
        textUsuario.setText(admin.getUsername());
        textSenha.setText(admin.getSenha());
        
    }    
    @FXML
    public void handleCancelar(MouseEvent event){
        buttonAlterar.setStyle(null);
        buttonAdicionar.setStyle(null);
        buttonRemover.setStyle(null);
        select = 0;
    }
    
    public void setTextVazio(){
        textUsuario.setText("");
        textSenha.setText("");
        textCPF.setText("");
        textNome.setText("");
        textTelefone.setText("");
        textEndereco.setText("");
        textRG.setText("");
    }    
    @FXML
    public void handleRemoverMotorista(MouseEvent event){
        buttonAlterar.setStyle(null);
        buttonAdicionar.setStyle(null);
        buttonRemover.setStyle("-fx-background-color: #f29696");
        select = 3;
        
    }
    @FXML
    public void handleAdicionarMotorista(MouseEvent event){
        buttonAlterar.setStyle(null);
        buttonAdicionar.setStyle("-fx-background-color: #98f296");
        buttonRemover.setStyle(null);        
        setTextVazio();
        select = 2;
        textUsuario.setEditable(true);
        textSenha.setEditable(true);        
        textCPF.setEditable(true);
        textNome.setEditable(true);
        textTelefone.setEditable(true);
        textEndereco.setEditable(true);
        textRG.setEditable(true);
       
        
    }
    @FXML    
    public void handleAlterarMotorista(MouseEvent eventt){
        
        buttonAlterar.setStyle("-fx-background-color: #f2ef96");
        buttonAdicionar.setStyle(null);
        buttonRemover.setStyle(null);

        select = 1;

        textUsuario.setEditable(true);
        textSenha.setEditable(true);        
        textCPF.setEditable(false);
        textNome.setEditable(true);
        textTelefone.setEditable(true);
        textEndereco.setEditable(true);
        textRG.setEditable(true);
    }    
    
    public void carregarTableView(){
        listAdmin = adminDAO.list();
        observableListAdmin = FXCollections.observableArrayList(listAdmin);
        tableColumnUsuario.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableColumnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

        tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableViewAdmin.setItems(observableListAdmin);           
    }
    
    public void search(){
        
    FilteredList<Administrador> filteredData = new FilteredList<>(observableListAdmin, b -> true);
    textBuscaNome.textProperty().addListener ((observable, oldValue, newValue) -> {
        filteredData.setPredicate(admin -> {
            if (newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (admin.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; 
            }            
            return false;
        });
    });    

    SortedList<Administrador> sortedData = new SortedList<>(filteredData);
    
    sortedData.comparatorProperty().bind(tableViewAdmin.comparatorProperty());
    tableViewAdmin.setItems(sortedData);    
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableView();
    }    
    
}
