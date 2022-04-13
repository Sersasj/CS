/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.CorridaDAO;
import com.sistema.model.dao.OnibusDAO;
import com.sistema.model.dao.ProblemaDAO;
import com.sistema.model.pojo.Corrida;
import com.sistema.model.pojo.Motorista;
import com.sistema.model.pojo.Onibus;
import com.sistema.model.pojo.Problema;
import com.sistema.model.pojo.TipoProblema;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sergi
 */

public class UIMobileEnviarProblemasController implements Initializable {
    
    
    @FXML
    TextField textFieldProblema;
    
    private Corrida corrida;
    private Problema problema = new Problema();
    private Motorista motorista;
    private List<Problema> listProblema;
    private Onibus onibus;
    private TipoProblema tipoProblema = new TipoProblema();
    private final CorridaDAO corridaDAO = new CorridaDAO();
    private final ProblemaDAO problemaDAO = new ProblemaDAO();
    private final OnibusDAO onibusDAO = new OnibusDAO();
    /**
     * Initializes the controller class.
     */
    @FXML
    public void handleCancelar(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIMobileFinalizar.fxml"));

            UIMobileFinalizarController controller = new UIMobileFinalizarController();
            controller.setCorrida(corrida);
            fxmlLoader.setController(controller);
            Parent root1 = (Parent) fxmlLoader.load();

            Scene scene = new Scene(root1);
            Stage stage = (Stage) textFieldProblema.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }    
    }
    @FXML 
    public void handleEnviar(MouseEvent event){
        onibus = corrida.getOnibus();
        motorista = corrida.getMotorista();
        tipoProblema.setId(1);
        tipoProblema.setTipo("Motor");
        problema.setIdTipo(tipoProblema);
        problema.setDescricao(textFieldProblema.getText());
        problema.setCpfMotorista(motorista);
        problema.setPlacaOnibus(onibus);
        problema.setDataHorario(corrida.getFimCorrida());     

        problemaDAO.add(problema);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIMobileFinalizar.fxml"));

            UIMobileFinalizarController controller = new UIMobileFinalizarController();
            controller.setCorrida(corrida);
            fxmlLoader.setController(controller);
            Parent root1 = (Parent) fxmlLoader.load();

            Scene scene = new Scene(root1);
            Stage stage = (Stage) textFieldProblema.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }
}
