/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.bd.EMFSingleton;
import com.sistema.model.pojo.Onibus;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.HibernateException;

/**
 *
 * @author vini
 */
public class OnibusDAO {

    public OnibusDAO() {
    }

    private EntityManager getEntityManager() {
//        EntityManagerFactory factory = null;
//        EntityManager entityManager = null;
//        try {
//            //Obtém o factory a partir da unidade de persistência.
//            factory = Persistence.createEntityManagerFactory("SistemaGerenciamentoOnibusPU");
//            //Cria um entity manager.
//            entityManager = factory.createEntityManager();
//            System.out.println("1 "+ entityManager.toString());
//            //Fecha o factory para liberar os recursos utilizado.
//        } finally {
//            System.out.println("2 "+ entityManager.toString());
//            factory.close();
//            System.out.println("3 "+ entityManager.toString());
//        }
        EMFSingleton emfSingleton = EMFSingleton.getInstance();
        EntityManager entityManager = emfSingleton.getEntityManagerFactory().createEntityManager();

        return entityManager;
    }

    public Onibus add(Onibus onibus) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(onibus);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return onibus;
//        Session session = getSessionFactory().openSession();
//        Transaction tx = null;
//        Integer onibusID = null;
//
//        try {
//            tx = session.beginTransaction();
//            onibusID = (Integer) session.save(onibus);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return onibusID;
    }

    public void update(Onibus onibus) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(onibus);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
//        Session session = getSessionFactory().openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            Onibus onibusAntigo = (Onibus) session.get(Onibus.class, onibus.getPlaca());
//            onibusAntigo.setAno(onibus.getAno());
//            onibusAntigo.setQuilometragem(onibus.getQuilometragem());
//            onibusAntigo.setModelo(onibus.getModelo());
//            session.update(onibusAntigo);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
    }

    public List<Onibus> list() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Onibus> listaRetorno = new ArrayList<>();
        try {

            System.out.println(entityManager.toString());
            transaction.begin();
            listaRetorno = entityManager.createQuery("SELECT e FROM Onibus e").getResultList();
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
//        Session session = getSessionFactory().openSession();
//        Transaction tx = null;
//        List<Onibus> listaRetorno = new ArrayList<>();
//
//        try {
//            tx = session.beginTransaction();
//            listaRetorno = session.createQuery("FROM " + Onibus.class.getName() + "").list();
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return listaRetorno;
    }

    public Onibus getById(String onibusId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Onibus onibus = null;
        try {
            transaction.begin();
            onibus = entityManager.find(Onibus.class, onibusId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return onibus;
//        Session session = getSessionFactory().openSession();
//        Transaction tx = null;
//        Onibus onibus = new Onibus();
//
//        try {
//            tx = session.beginTransaction();
//            onibus = (Onibus) session.load(Onibus.class, onibusId);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return onibus;
    }

    public boolean delete(String onibusId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Onibus onibus = getById(onibusId);
            if (onibus == null) {
                return false;
            }
            entityManager.remove(onibus);
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
//        Session session = getSessionFactory().openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            Onibus onibus = (Onibus) session.get(Onibus.class,
//                     onibusId);
//            session.delete(onibus);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
    }
}
