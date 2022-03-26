/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.CorridaDAO;
import com.sistema.model.pojo.Corrida;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.util.Random;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIMobileCorridaController implements Initializable {
    
    
    @FXML 
    private Text linhaOnibus, placaOnibus;
    @FXML
    private Button buttonEmergencia, buttonIniciar, buttonFinalizar;
    private Corrida corrida;
    
    private final CorridaDAO corridaDAO = new CorridaDAO();

    /**
     * Initializes the controller class.
     */
    
    
    public void simularCorrida(){
        Random rand = new Random();
        
        corrida.setPassPagantes(rand.nextInt(60) + 10);
        corrida.setPassNaoPagantes(rand.nextInt(40));
        corrida.setConsumoCombustivel(rand.nextFloat() * (float)20.0 + (float)3.0);
        corrida.setDistanciaPercorrida(rand.nextFloat() * (float)5.0 + (float)30.0);
        corrida.setConsumoCombustivel(rand.nextFloat() * 100);

    }
    
    @FXML
    public void handleFinalizar(MouseEvent event){
        Timestamp fim = new Timestamp(System.currentTimeMillis());
        simularCorrida();
        corrida.setFimCorrida(fim);
        corridaDAO.add(corrida);
    }
    @FXML
    public void handleIniciar(MouseEvent event){

        Timestamp inicio = new Timestamp(System.currentTimeMillis());
        corrida.setInicioCorrida(inicio);

        System.out.println(inicio);
        
        buttonIniciar.setDisable(true);
        buttonIniciar.setVisible(false);
        
        buttonEmergencia.setDisable(false);
        buttonFinalizar.setDisable(false);
        buttonEmergencia.setVisible(true);
        buttonFinalizar.setVisible(true);  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonEmergencia.setDisable(true);
        buttonFinalizar.setDisable(true);
        buttonEmergencia.setVisible(false);
        buttonFinalizar.setVisible(false);   
        

        linhaOnibus.setText(corrida.getLinha().toString());
        placaOnibus.setText(corrida.getOnibus().getPlaca());

    }    

    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }
    
}
