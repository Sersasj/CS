/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.util.EMFSingleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.HibernateException;

/**
 *
 * @author vini
 */
// T é o tipo da classe, K é o tipo da chave primária da classe
public abstract class GenericDAO<T, K extends Serializable> {

    private Class<T> classe;
    
    protected GenericDAO(Class<T> classe) {
        this.classe = classe;
    }

    protected EntityManager getEntityManager() {
        EMFSingleton emfSingleton = EMFSingleton.getInstance();
        EntityManager entityManager = emfSingleton.getEntityManagerFactory().createEntityManager();

        return entityManager;
    }

    public T add(T obj) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(obj);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return obj;
    }

    public void update(T obj) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(obj);
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

    public List<T> list() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<T> listaRetorno = new ArrayList<>();
        try {

            System.out.println(entityManager.toString());
            transaction.begin();
            listaRetorno = entityManager.createQuery("SELECT e FROM " + classe.getSimpleName() + " e").getResultList();
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

    public T getById(K objId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        T obj = null;
        try {
            transaction.begin();
            obj = entityManager.find(classe, objId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return obj;
    }

    public boolean remove(K objId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T obj = getById(objId);
            if (obj == null) {
                return false;
            }
            
            entityManager.remove(entityManager.merge(obj));
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
