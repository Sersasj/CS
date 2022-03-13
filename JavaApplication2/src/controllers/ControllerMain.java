/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.awt.event.ActionEvent;
import telaDesktop.UIOpcao;
import telaMobile.UIEntrarAplicativo;
import telaDesktop.UIDesktop;

/**
 *
 * @author sergi
 */
public class ControllerMain {
    private UIOpcao view;
    //construtor
    public ControllerMain(){
        
    }
    
    public ControllerMain(UIOpcao view){
        this.view = view;
        this.view.setVisible(true);
        
    } 

    public void controla() {
        view.getMobile().addActionListener((ActionEvent actionEvent) -> {
            view.dispose();
            new ControllerEntrarAplicativo(new UIEntrarAplicativo()).controla();
        });
        
        view.getDesktop().addActionListener((ActionEvent actionEvent) -> {
            view.dispose();
            new ControllerTelaDesktop(new UIDesktop()).controla();
        });

    }    
    
}
