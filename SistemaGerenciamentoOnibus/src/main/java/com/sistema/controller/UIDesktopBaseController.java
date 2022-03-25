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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vini
 */
public class UIDesktopBaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button buttonFechar, buttonMinimizar;
    @FXML VBox vBoxRoot;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTelaLogin();
    }

    public void iniciarTelaLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopLogin.fxml"));
            AnchorPane paneLogin = (AnchorPane) fxmlLoader.load();
            vBoxRoot.getChildren().add(paneLogin);
            VBox.setVgrow(paneLogin, Priority.ALWAYS);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
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
}
