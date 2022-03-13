/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import telaDesktop.UICadastroMotorista;
import telaDesktop.UIDesktop;
import telaMobile.UIApp;

/**
 *
 * @author sergi
 */
public class ControllerCadastroMotorista {
    private UICadastroMotorista view;
    
    public ControllerCadastroMotorista(){
        
    }
    public ControllerCadastroMotorista(UICadastroMotorista view){
        this.view = view;
        this.view.setVisible(true);
        
    }    
    public void controla(){
        
        view.getFechar().addActionListener((ActionEvent actionEvent) -> {
            view.dispose();
            new ControllerTelaDesktop(new UIDesktop()).controla();
        });  
        view.getSalvar().addActionListener((ActionEvent actionEvent) -> {
            JOptionPane.showMessageDialog(view,
        "Motorista cadastrado com sucesso");            
            view.dispose();
            new ControllerTelaDesktop(new UIDesktop()).controla();
        });          
        
    }
}
