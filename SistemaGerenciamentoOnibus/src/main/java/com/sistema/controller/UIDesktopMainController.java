/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.pojo.Corrida;
import com.sistema.util.Mediator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIDesktopMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button buttonFechar, buttonMinimizar, buttonToggleMenu;
    @FXML
    private AnchorPane anchorPaneMapa, anchorPaneOpacidade;
    @FXML
    private HBox hBoxMenuAberto, hBoxMain;
    @FXML
    private StackPane stackPaneRoot;
    @FXML
    private AnchorPane anchorPaneConteudo;
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializaMapa();
        Mediator.getInstance().registerControllerDesktop(this);
        hBoxMenuAberto.setDisable(true);
        hBoxMenuAberto.setVisible(false);
    }
    
    @FXML
    public void inicializaMapa(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopMapa.fxml"));         

            AnchorPane paneMapa = (AnchorPane) fxmlLoader.load();
            
            anchorPaneConteudo.getChildren().setAll(paneMapa);
            anchorPaneConteudo.setTopAnchor(paneMapa, 0.0);
            anchorPaneConteudo.setLeftAnchor(paneMapa, 0.0);
            anchorPaneConteudo.setRightAnchor(paneMapa, 0.0);
            anchorPaneConteudo.setBottomAnchor(paneMapa, 0.0);
        } catch(IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }  
    }
    
    @FXML
    public void handleButtonMapa(MouseEvent event){
        inicializaMapa();
    }
    
    @FXML
    public void handleButtonMotorista(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopCRUDMotorista.fxml"));         

            AnchorPane paneCRUDMotorista = (AnchorPane) fxmlLoader.load();
            
            
            
            anchorPaneConteudo.getChildren().setAll(paneCRUDMotorista);
            anchorPaneConteudo.setTopAnchor(paneCRUDMotorista, 0.0);
            anchorPaneConteudo.setLeftAnchor(paneCRUDMotorista, 0.0);
            anchorPaneConteudo.setRightAnchor(paneCRUDMotorista, 0.0);
            anchorPaneConteudo.setBottomAnchor(paneCRUDMotorista, 0.0);
        } catch(IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }  
    }
    
    @FXML void handleButtonAdmin(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopCRUDAdmin.fxml"));         

            AnchorPane paneCRUDAdmin = (AnchorPane) fxmlLoader.load();
            
            
            
            anchorPaneConteudo.getChildren().setAll(paneCRUDAdmin);
            anchorPaneConteudo.setTopAnchor(paneCRUDAdmin, 0.0);
            anchorPaneConteudo.setLeftAnchor(paneCRUDAdmin, 0.0);
            anchorPaneConteudo.setRightAnchor(paneCRUDAdmin, 0.0);
            anchorPaneConteudo.setBottomAnchor(paneCRUDAdmin, 0.0);
        } catch(IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }        
    }
    @FXML 
    public void handleButtonOnibus(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopCRUDOnibus.fxml"));         

            AnchorPane paneCRUDOnibus = (AnchorPane) fxmlLoader.load();
            
            
            
            anchorPaneConteudo.getChildren().setAll(paneCRUDOnibus);
            anchorPaneConteudo.setTopAnchor(paneCRUDOnibus, 0.0);
            anchorPaneConteudo.setLeftAnchor(paneCRUDOnibus, 0.0);
            anchorPaneConteudo.setRightAnchor(paneCRUDOnibus, 0.0);
            anchorPaneConteudo.setBottomAnchor(paneCRUDOnibus, 0.0);
        } catch(IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }  
    }
    
    @FXML
    public void popUpEmergencia(){
        // vai usar uma corrida como parametro
        System.out.println("emergencia ativada");
    }
    
    @FXML
    public void handleButtonFechar(ActionEvent event) {
        Stage stage = (Stage) buttonFechar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void handleButtonMinimizar(ActionEvent event) {
        Stage stage = (Stage) buttonMinimizar.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    public void handleToggleMenu(ActionEvent event) {
        hBoxMenuAberto.setDisable(!hBoxMenuAberto.isDisable());
        hBoxMenuAberto.setVisible(!hBoxMenuAberto.isVisible());
    }
    
    @FXML
    public void handlePaneOpacidade(MouseEvent event) {
        hBoxMenuAberto.setDisable(true);
        hBoxMenuAberto.setVisible(false);
    }
    
}
