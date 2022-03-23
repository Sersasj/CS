/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Onibus;
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
public class OnibusDAO {
    public static SessionFactory getSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
 
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
 
        SessionFactory factoryObj = configObj.buildSessionFactory(serviceRegistryObj);      
        return factoryObj;
    }

    public Integer add(Onibus onibus) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Integer onibusID = null;

        try {
            tx = session.beginTransaction();
            onibusID = (Integer) session.save(onibus);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return onibusID;
    }
    
    public void update(Onibus onibus) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Onibus onibusAntigo = (Onibus) session.get(Onibus.class, onibus.getPlaca());
            onibusAntigo.setAno(onibus.getAno());
            onibusAntigo.setQuilometragem(onibus.getQuilometragem());
            onibusAntigo.setModelo(onibus.getModelo());
            session.update(onibusAntigo);
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
    
    public List<Onibus> list() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Onibus> listaRetorno = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            listaRetorno = session.createQuery("FROM " + Onibus.class.getName() + "").list();
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
    

    public Onibus getById(Integer onibusId) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Onibus onibus = new Onibus();

        try {
            tx = session.beginTransaction();
            onibus = (Onibus) session.load(Onibus.class, onibusId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return onibus;
    }

    public void delete(Integer onibusId) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Onibus onibus = (Onibus) session.get(Onibus.class, onibusId);
            session.delete(onibus);
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
