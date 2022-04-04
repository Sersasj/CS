/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author vini
 */
public class UIDesktopMapaController implements Initializable {
    @FXML
    private WebView webView;
    @FXML
    private WebEngine webEngine;
    @FXML
    private Button btnReloadMapa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = webView.getEngine();
        webEngine.getLoadWorker().stateProperty()
        .addListener((obs, oldValue, newValue) -> {
          if (newValue == State.SUCCEEDED) {
            System.out.println("finished loading");
          }
        });
        //webEngine.load("http://html5test.com");
        webEngine.load(getClass().getResource("/mapa/googlemaps.html").toString());
        
    }
    
    @FXML
    public void handleReload(MouseEvent event) {
        webEngine.load(getClass().getResource("/mapa/googlemaps.html").toString());
    }
    
}
