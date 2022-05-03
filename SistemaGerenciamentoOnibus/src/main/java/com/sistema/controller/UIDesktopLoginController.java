/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.AdministradorDAO;
import com.sistema.model.pojo.Administrador;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author vini
 */
public class UIDesktopLoginController implements Initializable {

    @FXML
    PasswordField txtFieldSenha;
    @FXML
    TextField txtFieldUsuario;
    @FXML
    Button btnLogin, btnRegistrar;
    @FXML
    AnchorPane paneRoot;
    @FXML
    Label lblLoginIncorreto;
    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFieldUsuario.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtFieldSenha.requestFocus();
            }
        });
        txtFieldSenha.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                handleLogin();
            }
        });
    }

    public void handleLogin() {
        lblLoginIncorreto.setText("");
        String usuario = txtFieldUsuario.getText();
        String senha = txtFieldSenha.getText();
        Administrador administrador = administradorDAO.getByUsername(usuario);
        System.out.println(administrador.toString());
        if (administrador.getUsername() != null) {
            if (administrador.getSenha().equals(senha)) {
//                login feito
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopMain.fxml"));
                    StackPane paneMain = (StackPane) fxmlLoader.load();
                    paneRoot.getChildren().setAll(paneMain);
                    AnchorPane.setTopAnchor(paneMain, 0.0);
                    AnchorPane.setLeftAnchor(paneMain, 0.0);
                    AnchorPane.setRightAnchor(paneMain, 0.0);
                    AnchorPane.setBottomAnchor(paneMain, 0.0);
                } catch (IOException e) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to create new Window.", e);
                }
            } else {
                lblLoginIncorreto.setText("Senha incorreta");
            }
        } else {
            lblLoginIncorreto.setText("Usuário não registrado");
        }
    }
    
    public void handleCadastrar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopCadastroAdmin.fxml"));
            AnchorPane paneMain = (AnchorPane) fxmlLoader.load();
            paneRoot.getChildren().setAll(paneMain);
            AnchorPane.setTopAnchor(paneMain, 0.0);
            AnchorPane.setLeftAnchor(paneMain, 0.0);
            AnchorPane.setRightAnchor(paneMain, 0.0);
            AnchorPane.setBottomAnchor(paneMain, 0.0);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
}
