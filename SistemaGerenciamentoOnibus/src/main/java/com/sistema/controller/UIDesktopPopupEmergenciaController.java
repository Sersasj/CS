/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.pojo.Corrida;
import com.sistema.util.StringFormatter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author vini
 */
public class UIDesktopPopupEmergenciaController implements Initializable {
    private Corrida corrida;
    private StringFormatter formatter = new StringFormatter();
    @FXML
    private Label labelMotorista, labelTelefone, labelOnibus, labelLinha, labelPosicao;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelMotorista.setText(corrida.getMotorista().getNome());
        labelTelefone.setText(formatter.formatarTelefoneDisplay(corrida.getMotorista().getTelefone()));
        labelOnibus.setText(formatter.formatarPlacaDisplay(corrida.getOnibus().getPlaca()));
        labelLinha.setText(corrida.getLinha().toString());
        labelPosicao.setText(corrida.coordenadasToString());
    }    

    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }
    
}
