/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Corrida;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.hibernate.HibernateException;

/**
 *
 * @author sergi
 */
public class CorridaDAO extends GenericDAO<Corrida, Integer>{
   
    public CorridaDAO() {
        super(Corrida.class);
    }
    
    public List<Corrida> list(Date dataInicial, Date dataFinal) {
        EntityManager entityManager = super.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Corrida> listaRetorno = new ArrayList<>();
        try {
            System.out.println(entityManager.toString());
            transaction.begin();
            if(dataInicial == null) dataInicial = new Date(0L);
            if(dataFinal == null) dataFinal = new Date(System.currentTimeMillis());
            Query query = entityManager.createQuery("SELECT c FROM Corrida c WHERE c.inicioCorrida > :dataInicial AND  c.fimCorrida < :dataFinal");
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);
            listaRetorno = query.getResultList();
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
