/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Linha;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.HibernateException;

/**
 *
 * @author vini
 */
public class LinhaDAO extends GenericDAO<Linha, Integer>{
    public LinhaDAO() {
        super(Linha.class);
    }
    
    public List<Linha> listComPontos() {
        EntityManager entityManager = super.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Linha> listaRetorno = new ArrayList<>();
        try {
            System.out.println(entityManager.toString());
            transaction.begin();
            listaRetorno = entityManager.createQuery("SELECT DISTINCT e FROM Linha e LEFT JOIN FETCH e.pontoList").getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return listaRetorno;
    }
}
