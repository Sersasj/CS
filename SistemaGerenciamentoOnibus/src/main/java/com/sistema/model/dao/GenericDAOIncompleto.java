/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author vini
 */
public class GenericDAOIncompleto<Tipo, TipoId extends Serializable>{
    /*private Class<Tipo> classe;
    private Class<TipoId> classeId;
    
    protected GenericDAOIncompleto(Class<Tipo> classe, Class<TipoId> classeId) {
        this.classe = classe;
        this.classe = classeId;
    }
    
    public static SessionFactory getSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
 
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
 
        SessionFactory factoryObj = configObj.buildSessionFactory(serviceRegistryObj);      
        return factoryObj;
    }

    public Integer add(Tipo obj) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Integer objID = null;

        try {
            tx = session.beginTransaction();
            objID = (Integer) session.save(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return objID;
    }
    
    public void update(Tipo obj) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Tipo objAntigo = (Tipo) session.get(classe, obj.getId());
            objAntigo.setLatitude(obj.getLatitude());
            objAntigo.setLongitude(obj.getLongitude());
            session.update(objAntigo);
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
    
    public List<Tipo> list() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Tipo> listaRetorno = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            List objs = session.createQuery("FROM Tipo").list();
            for (Iterator iterator = objs.iterator(); iterator.hasNext();) {
                Tipo obj = (Tipo) iterator.next();
                listaRetorno.add(obj);
            }
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
    

    public Tipo getById(Integer objId) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Tipo obj = new Tipo();

        try {
            tx = session.beginTransaction();
            obj = (Tipo) session.load(Tipo.class, objId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return obj;
    }

    public void delete(Integer objId) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Tipo obj = (Tipo) session.get(Tipo.class, objId);
            session.delete(obj);
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
*/
}
