/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.AdministradorDAO;
import com.sistema.model.pojo.Administrador;
import com.sistema.util.StringFormatter;
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
 *
 * @author vini
 */
public class UIDesktopCadastroAdminController implements Initializable {

    @FXML
    PasswordField txtFieldSenha, txtFieldConfirmarSenha;
    @FXML
    TextField txtFieldUsuario, txtFieldNome, txtFieldCPF, txtFieldRG, txtFieldEndereco, txtFieldTelefone;
    @FXML
    AnchorPane paneRoot;
    @FXML
    Label lblLoginIncorreto;
    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    private StringFormatter stringFormatter = new StringFormatter();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFieldNome.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtFieldCPF.requestFocus();
            }
        });
        txtFieldCPF.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtFieldRG.requestFocus();
            }
        });
        txtFieldRG.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtFieldEndereco.requestFocus();
            }
        });
        txtFieldEndereco.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtFieldTelefone.requestFocus();
            }
        });
        txtFieldTelefone.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtFieldUsuario.requestFocus();
            }
        });
        txtFieldUsuario.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtFieldSenha.requestFocus();
            }
        });
        txtFieldSenha.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                txtFieldConfirmarSenha.requestFocus();
            }
        });
        txtFieldSenha.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                handleCadastrar();
            }
        });
    }

    public void handleCadastrar() {
        lblLoginIncorreto.setText("");
        lblLoginIncorreto.setStyle("-fx-text-fill: #ff7474");
        String usuario = txtFieldUsuario.getText();
        String senha = txtFieldSenha.getText();
        Administrador administrador = administradorDAO.getByUsername(usuario);
        System.out.println(administrador.toString());
        if (senha.equals(txtFieldConfirmarSenha.getText())) {
            if (stringFormatter.validarCPF(txtFieldCPF.getText()) && stringFormatter.validarRG(txtFieldRG.getText())) {
                if (administrador.getUsername() == null) {
                    administrador = new Administrador();
                    administrador.setNome(txtFieldNome.getText());
                    administrador.setCpf(stringFormatter.formatarCPF(txtFieldCPF.getText()));
                    administrador.setRg(stringFormatter.formatarRG(txtFieldRG.getText()));
                    administrador.setEndereco(txtFieldEndereco.getText());
                    administrador.setTelefone(stringFormatter.formatarTelefone(txtFieldTelefone.getText()));
                    administrador.setUsername(txtFieldUsuario.getText());
                    administrador.setSenha(txtFieldSenha.getText());
                    lblLoginIncorreto.setText("Usuário cadastrado com sucesso");
                    lblLoginIncorreto.setStyle("-fx-text-fill: whitesmoke");
                } else {
                    lblLoginIncorreto.setText("Usuário já cadastrado");
                }
            } else {
                lblLoginIncorreto.setText("CPF ou RG inválidos");
            }
        } else {
            lblLoginIncorreto.setText("Confirmação de senha incorreta");
        }
    }

    public void handleVoltar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopLogin.fxml"));
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
