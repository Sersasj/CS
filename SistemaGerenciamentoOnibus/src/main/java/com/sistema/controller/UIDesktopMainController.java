/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.pojo.Corrida;
import com.sistema.util.Mediator;
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
import javafx.stage.Modality;
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
    private AnchorPane anchorPaneConteudo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTela("/com/sistema/view/UIDesktopMapa.fxml");
        Mediator.getInstance().registerControllerDesktop(this);
        hBoxMenuAberto.setDisable(true);
        hBoxMenuAberto.setVisible(false);
    }
    
    public void carregarTela(String enderecoTela){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(enderecoTela));

            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

            anchorPaneConteudo.getChildren().setAll(anchorPane);
            anchorPaneConteudo.setTopAnchor(anchorPane, 0.0);
            anchorPaneConteudo.setLeftAnchor(anchorPane, 0.0);
            anchorPaneConteudo.setRightAnchor(anchorPane, 0.0);
            anchorPaneConteudo.setBottomAnchor(anchorPane, 0.0);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
            
    @FXML
    public void handleButtonMapa(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopMapa.fxml");
    }

    @FXML
    public void handleButtonMotorista(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopCRUDMotorista.fxml");
    }

    @FXML
    void handleButtonAdmin(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopCRUDAdmin.fxml");
    }

    @FXML
    public void handleButtonOnibus(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopCRUDOnibus.fxml");
    }
    
    @FXML
    public void handleButtonHistorico(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopHistoricoCorridas.fxml");
    }
    
    @FXML
    public void handleButtonRelatorio(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopRelatorio.fxml");
    }

    @FXML
    public void popUpEmergencia(Corrida corrida) {
        try {
            // vai usar uma corrida como parametro
            System.out.println("emergencia ativada");
            System.out.println(corrida.toString());
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopPopupEmergencia.fxml"));
            UIDesktopPopupEmergenciaController controller = new UIDesktopPopupEmergenciaController();
            controller.setCorrida(corrida);
            fxmlLoader.setController(controller);
            Parent popupEmergencia = fxmlLoader.load();
            
            Stage stageEmergencia = new Stage();
            stageEmergencia.initModality(Modality.APPLICATION_MODAL);
            stageEmergencia.setScene(new Scene(popupEmergencia));
            stageEmergencia.show();
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

    @FXML
    public void handleToggleMenu(ActionEvent event) {
        hBoxMenuAberto.setDisable(!hBoxMenuAberto.isDisable());
        hBoxMenuAberto.setVisible(!hBoxMenuAberto.isVisible());
    }

    @FXML
    public void handlePaneOpacidade(MouseEvent event) {
        hBoxMenuAberto.setDisable(true);
        hBoxMenuAberto.setVisible(false);
    }

}
