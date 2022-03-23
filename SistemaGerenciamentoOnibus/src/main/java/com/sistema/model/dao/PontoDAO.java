/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Ponto;
import java.util.ArrayList;
import org.hibernate.cfg.Configuration;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author vini
 */
public class PontoDAO {
    public static SessionFactory getSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
 
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
 
        SessionFactory factoryObj = configObj.buildSessionFactory(serviceRegistryObj);      
        return factoryObj;
    }

    public Integer add(Ponto ponto) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Integer pontoID = null;

        try {
            tx = session.beginTransaction();
            pontoID = (Integer) session.save(ponto);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pontoID;
    }
    
    public void update(Ponto ponto) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Ponto pontoAntigo = (Ponto) session.get(Ponto.class, ponto.getId());
            pontoAntigo.setLatitude(ponto.getLatitude());
            pontoAntigo.setLongitude(ponto.getLongitude());
            session.update(pontoAntigo);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public List<Ponto> list() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Ponto> listaRetorno = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            listaRetorno = session.createQuery("FROM " + Ponto.class.getName() + "").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listaRetorno;
    }
    

    public Ponto getById(Integer pontoId) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Ponto ponto = new Ponto();

        try {
            tx = session.beginTransaction();
            ponto = (Ponto) session.load(Ponto.class, pontoId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ponto;
    }

    public void delete(Integer pontoId) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Ponto ponto = (Ponto) session.get(Ponto.class, pontoId);
            session.delete(ponto);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

