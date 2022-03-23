/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Linha;
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
public class LinhaDAO {
    public static SessionFactory getSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
 
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
 
        SessionFactory factoryObj = configObj.buildSessionFactory(serviceRegistryObj);      
        return factoryObj;
    }

    public Integer add(Linha linha) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Integer linhaID = null;

        try {
            tx = session.beginTransaction();
            linhaID = (Integer) session.save(linha);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return linhaID;
    }
    
    public void update(Linha linha) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Linha linhaAntigo = (Linha) session.get(Linha.class, linha.getNumero());
            linhaAntigo.setNome(linha.getNome());
            session.update(linhaAntigo);
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
    
    public List<Linha> list() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Linha> listaRetorno = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            List linhas = session.createQuery("FROM " + Linha.class.getName() + "").list();
            for (Iterator iterator = linhas.iterator(); iterator.hasNext();) {
                Linha linha = (Linha) iterator.next();
                listaRetorno.add(linha);
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
    

    public Linha getById(Integer linhaId) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Linha linha = new Linha();

        try {
            tx = session.beginTransaction();
            linha = (Linha) session.load(Linha.class, linhaId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return linha;
    }

    public void delete(Integer linhaId) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Linha linha = (Linha) session.get(Linha.class, linhaId);
            session.delete(linha);
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
