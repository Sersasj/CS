/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.LinhaDAO;
import com.sistema.model.pojo.Linha;
import com.sistema.util.ValidadorString;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIMobileEntrarController implements Initializable {

    @FXML
    private ComboBox<Linha> comboBoxLinhas;
    @FXML
    private TextField txtFieldPlacaOnibus, txtFieldNumeroCpf;
    @FXML
    private Button botaoConfirmar;

    private List<Linha> listLinhas;
    private ObservableList<Linha> observableListLinhas;

    private final LinhaDAO linhaDAO = new LinhaDAO();
    private ValidadorString validadorString = new ValidadorString();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboBoxLinhas();

        txtFieldNumeroCpf.setTextFormatter(new TextFormatter<>((change) -> {
            String novaString = change.getControlNewText();
            System.out.println(validadorString.formatarCPFIncompleto(novaString));
            if (ValidadorString.PATTERN_CPF_INCOMPLETO.matcher(novaString).matches()) {
                return change; 
            } else {
                return null;
            }
        }));

        txtFieldPlacaOnibus.setTextFormatter(new TextFormatter<>((change) -> {
            if (change.getText().matches("[a-z]")) {
                change.setText(change.getText().toUpperCase());
            }
            String novaString = change.getControlNewText();
            System.out.println(novaString);
            if (ValidadorString.PATTERN_PLACA_INCOMPLETA.matcher(novaString).matches()) {
                return change; 
            } else {
                return null;
            }
            
        }));
    }

    public void carregarComboBoxLinhas() {
        listLinhas = linhaDAO.list();
        observableListLinhas = FXCollections.observableArrayList(listLinhas);
        comboBoxLinhas.setItems(observableListLinhas);
        //mostrar nome
    }

}
