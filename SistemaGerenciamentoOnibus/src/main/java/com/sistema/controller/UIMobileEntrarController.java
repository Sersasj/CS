/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.LinhaDAO;
import com.sistema.model.dao.MotoristaDAO;
import com.sistema.model.dao.OnibusDAO;
import com.sistema.model.pojo.Corrida;
import com.sistema.model.pojo.Linha;
import com.sistema.model.pojo.Motorista;
import com.sistema.model.pojo.Onibus;
import com.sistema.util.ValidadorString;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private final OnibusDAO onibusDAO = new OnibusDAO(); 
    private final MotoristaDAO motoristaDAO = new MotoristaDAO();
    private ValidadorString validadorString = new ValidadorString();

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void handleBotaoConfirmar(MouseEvent event){
        String placaFormatada = validadorString.formatarPlaca(txtFieldPlacaOnibus.getText());
        String cpfFormatado = validadorString.formatarCPF(txtFieldNumeroCpf.getText());
        
        Onibus onibus = onibusDAO.getById(placaFormatada);
        Motorista motorista = motoristaDAO.getById(cpfFormatado);
        Linha linha = comboBoxLinhas.getValue();
        if ((onibus == null) || (motorista == null)){
            return;
        }
        
        Corrida corrida = new Corrida();
        corrida.setMotorista(motorista);
        corrida.setOnibus(onibus);
        corrida.setLinha(linha);


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIMobileCorrida.fxml"));
            
                        //Parent root1 = (Parent) fxmlLoader.load();

            UIMobileCorridaController controller = new UIMobileCorridaController();
            controller.setCorrida(corrida);
            fxmlLoader.setController(controller);
            Parent root1 = (Parent) fxmlLoader.load();

            Scene scene = new Scene(root1);
            Stage stage = (Stage) botaoConfirmar.getScene().getWindow();
            
            stage.setScene(scene);
            stage.show();
        } catch(IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }        
        
        
    }
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
