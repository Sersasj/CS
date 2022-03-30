/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private AnchorPane anchorPaneRoot;
    @FXML
    public void handleButtonMotorista(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopCRUDMotorista.fxml"));         

            AnchorPane paneCRUDMotorista = (AnchorPane) fxmlLoader.load();
            
            
            
            anchorPaneRoot.getChildren().setAll(paneCRUDMotorista);
            anchorPaneRoot.setTopAnchor(paneCRUDMotorista, 0.0);
            anchorPaneRoot.setLeftAnchor(paneCRUDMotorista, 0.0);
            anchorPaneRoot.setRightAnchor(paneCRUDMotorista, 0.0);
            anchorPaneRoot.setBottomAnchor(paneCRUDMotorista, 0.0);
        } catch(IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }  
    }
    
    @FXML void handleButtonAdmin(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopCRUDAdmin.fxml"));         

            AnchorPane paneCRUDAdmin = (AnchorPane) fxmlLoader.load();
            
            
            
            anchorPaneRoot.getChildren().setAll(paneCRUDAdmin);
            anchorPaneRoot.setTopAnchor(paneCRUDAdmin, 0.0);
            anchorPaneRoot.setLeftAnchor(paneCRUDAdmin, 0.0);
            anchorPaneRoot.setRightAnchor(paneCRUDAdmin, 0.0);
            anchorPaneRoot.setBottomAnchor(paneCRUDAdmin, 0.0);
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
            
            
            
            anchorPaneRoot.getChildren().setAll(paneCRUDOnibus);
            anchorPaneRoot.setTopAnchor(paneCRUDOnibus, 0.0);
            anchorPaneRoot.setLeftAnchor(paneCRUDOnibus, 0.0);
            anchorPaneRoot.setRightAnchor(paneCRUDOnibus, 0.0);
            anchorPaneRoot.setBottomAnchor(paneCRUDOnibus, 0.0);
        } catch(IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }  
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hBoxMenuAberto.setDisable(true);
        hBoxMenuAberto.setVisible(false);
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
}
