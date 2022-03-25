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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

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
    private final AdministradorDAO administradorDAO = new AdministradorDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFieldSenha.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                handleLogin();
            }
        });
    }

    public void handleLogin() {
        String usuario = txtFieldUsuario.getText();
        String senha = txtFieldSenha.getText();
        Administrador administrador = administradorDAO.getByUsername(usuario);
        System.out.println(administrador.toString());
        if (administrador.getUsername() != null) {
            if (administrador.getSenha().equals(senha)) {
//                login feito
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopMain.fxml"));
                    AnchorPane paneMain = (AnchorPane) fxmlLoader.load();
                    paneRoot.getChildren().clear();
                    paneRoot.getChildren().add(paneMain);
                } catch (IOException e) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to create new Window.", e);
                }
                System.out.println("sucesso");
            } else {
                System.out.println("senha errada");
            }
        } else {
            System.out.println("user n existe");
        }
    }
}
