/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.util;

import com.sistema.controller.UIDesktopMainController;
import com.sistema.controller.UIMobileCorridaController;
import com.sistema.model.pojo.Corrida;
import java.util.ArrayList;

/**
 *
 * @author vini
 */
public class Mediator {
    
    private UIDesktopMainController controllerDesktop;
    private ArrayList<UIMobileCorridaController> listControllerMobile = new ArrayList();
    
    public void registerControllerDesktop(UIDesktopMainController ctrlDesktop) {
        controllerDesktop = ctrlDesktop;
    }

    public void registerControllerMobile(UIMobileCorridaController ctrlMobile) {
        listControllerMobile.add(ctrlMobile);
    }
    
    public void handleEmergencia(Corrida corrida){
        if (controllerDesktop != null)
            controllerDesktop.popUpEmergencia(corrida);
    }

    private static Mediator instance;

    public static Mediator getInstance() {
        if (instance == null) {
            instance = new Mediator();
        }
        return instance;
    }

    private Mediator(){}
}
