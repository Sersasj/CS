/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.CorridaDAO;
import com.sistema.model.pojo.Corrida;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gcpav
 */
public class UIMobileFinalizarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML 
    Text placaOnibus, linhaOnibus;
    @FXML
    Button buttonContinuar, buttonContinuarLinha, buttonContinuarMotorista;    
    
    private Corrida corrida;
    private final CorridaDAO corridaDAO = new CorridaDAO();

    @FXML
    public void handleContinuar (MouseEvent event) {
        buttonContinuar.setDisable(true);
        buttonContinuar.setVisible(false);
        buttonContinuarLinha.setDisable(false);
        buttonContinuarLinha.setVisible(true);
        buttonContinuarMotorista.setDisable(false);
        buttonContinuarMotorista.setVisible(true);        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonContinuarLinha.setDisable(true);
        buttonContinuarLinha.setVisible(false);
        buttonContinuarMotorista.setDisable(true);
        buttonContinuarMotorista.setVisible(false);
        
        placaOnibus.setText("Placa: " + corrida.getOnibus().getPlaca());
        linhaOnibus.setText("Linha: " + corrida.getLinha().toString());
        
    }    
    @FXML
    public void handleProblema(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIMobileEnviarProblemas.fxml"));

            UIMobileEnviarProblemasController controller = new UIMobileEnviarProblemasController();
            controller.setCorrida(corrida);
            fxmlLoader.setController(controller);
            Parent root1 = (Parent) fxmlLoader.load();

            Scene scene = new Scene(root1);
            Stage stage = (Stage) placaOnibus.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }         
    }
    @FXML
    public void handleVoltar(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIMobileEntrar.fxml"));         

            Parent root1 = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root1);
            Stage stage = (Stage) placaOnibus.getScene().getWindow();            
            stage.setScene(scene);
            stage.show();
        } catch(IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }        
        
    }
    
    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }
}
