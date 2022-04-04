/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.util;

/**
 *
 * @author vini
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFSingleton {

    private static EMFSingleton instance;
    protected EntityManagerFactory emf;

    public static EMFSingleton getInstance() {
        if (instance == null) {
            instance = new EMFSingleton();
        }
        return instance;
    }

    private EMFSingleton() {
    }
    
    public EntityManagerFactory getEntityManagerFactory() {

        if (emf == null) {
            createEntityManagerFactory();
        }
        return emf;
    }

    public void closeEntityManagerFactory() {

        if (emf != null) {
            emf.close();
            emf = null;
        }
    }

    protected void createEntityManagerFactory() {

        this.emf = Persistence.createEntityManagerFactory("SistemaGerenciamentoOnibusPU");
    }
}
