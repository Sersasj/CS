/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIDesktopController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private MenuItem menuCadastroAdministrador;
    @FXML
    private MenuItem menuCadastroMotorista;   
    @FXML
    private MenuItem menuCadastroOnibus;
    @FXML
    private AnchorPane anchorPane;     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
