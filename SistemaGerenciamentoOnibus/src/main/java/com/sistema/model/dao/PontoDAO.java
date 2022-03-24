/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.bd.EMFSingleton;
import com.sistema.model.pojo.Ponto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.HibernateException;

/**
 *
 * @author vini
 */
public class PontoDAO {

    public PontoDAO() {
    }
    
    private EntityManager getEntityManager() {
        EMFSingleton emfSingleton = EMFSingleton.getInstance();
        EntityManager entityManager = emfSingleton.getEntityManagerFactory().createEntityManager();

        return entityManager;
    }

    public Ponto add(Ponto ponto) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(ponto);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return ponto;
    }

    public void update(Ponto ponto) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(ponto);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Ponto> list() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Ponto> listaRetorno = new ArrayList<>();
        try {

            System.out.println(entityManager.toString());
            transaction.begin();
            listaRetorno = entityManager.createQuery("SELECT e FROM Ponto e").getResultList();
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

    public Ponto getById(Integer pontoId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Ponto ponto = null;
        try {
            transaction.begin();
            ponto = entityManager.find(Ponto.class, pontoId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return ponto;
    }

    public boolean delete(Integer pontoId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Ponto ponto = getById(pontoId);
            if (ponto == null) {
                return false;
            }
            entityManager.remove(ponto);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return true;
    }
}

