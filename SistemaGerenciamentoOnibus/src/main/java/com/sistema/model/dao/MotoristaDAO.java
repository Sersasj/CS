/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Motorista;
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
public class MotoristaDAO {
    public static SessionFactory getSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
 
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
 
        SessionFactory factoryObj = configObj.buildSessionFactory(serviceRegistryObj);      
        return factoryObj;
    }

    public Integer add(Motorista motorista) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Integer motoristaID = null;

        try {
            tx = session.beginTransaction();
            motoristaID = (Integer) session.save(motorista);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return motoristaID;
    }
    
    public void update(Motorista motorista) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            Motorista motoristaAntigo = (Motorista) session.get(Motorista.class, motorista.getCpf());
            motoristaAntigo.setRg(motorista.getRg());
            motoristaAntigo.setNome(motorista.getNome());
            motoristaAntigo.setTelefone(motorista.getTelefone());
            motoristaAntigo.setEndereco(motorista.getEndereco());
            motoristaAntigo.setCnh(motorista.getCnh());
            session.update(motoristaAntigo);
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
    
    public List<Motorista> list() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Motorista> listaRetorno = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            List motoristas = session.createQuery("FROM " + Motorista.class.getName() + "").list();
            for (Iterator iterator = motoristas.iterator(); iterator.hasNext();) {
                Motorista motorista = (Motorista) iterator.next();
                listaRetorno.add(motorista);
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
    

    public Motorista getById(Integer motoristaId) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Motorista motorista = new Motorista();

        try {
            tx = session.beginTransaction();
            motorista = (Motorista) session.load(Motorista.class, motoristaId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return motorista;
    }

    public void delete(Integer motoristaId) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Motorista motorista = (Motorista) session.get(Motorista.class, motoristaId);
            session.delete(motorista);
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
