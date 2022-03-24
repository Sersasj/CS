/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.bd.EMFSingleton;
import com.sistema.model.pojo.Motorista;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.HibernateException;

/**
 *
 * @author vini
 */
public class MotoristaDAO {
    public MotoristaDAO() {
    }
    
    private EntityManager getEntityManager() {
        EMFSingleton emfSingleton = EMFSingleton.getInstance();
        EntityManager entityManager = emfSingleton.getEntityManagerFactory().createEntityManager();

        return entityManager;
    }

    public Motorista add(Motorista motorista) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(motorista);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return motorista;
    }

    public void update(Motorista motorista) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(motorista);
            transaction.commit();
            System.out.println("foi");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Motorista> list() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Motorista> listaRetorno = new ArrayList<>();
        try {

            System.out.println(entityManager.toString());
            transaction.begin();
            listaRetorno = entityManager.createQuery("SELECT e FROM Motorista e").getResultList();
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

    public Motorista getById(String motoristaId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Motorista motorista = null;
        try {
            transaction.begin();
            motorista = entityManager.find(Motorista.class, motoristaId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return motorista;
    }

    public boolean delete(String motoristaId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Motorista motorista = getById(motoristaId);
            if (motorista == null) {
                return false;
            }
            entityManager.remove(motorista);
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
