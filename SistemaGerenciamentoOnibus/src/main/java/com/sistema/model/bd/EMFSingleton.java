/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.bd;

/**
 *
 * @author vini
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFSingleton {

    private static EMFSingleton singleton;
    protected EntityManagerFactory emf;

    public static EMFSingleton getInstance() {
        if (singleton == null) {
            singleton = new EMFSingleton();
        }
        return singleton;
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
