/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.model.dao;

import com.sistema.model.pojo.Administrador;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;

/**
 *
 * @author vini
 */
public class AdministradorDAO  extends GenericDAO<Administrador, String>{
    public AdministradorDAO() {
        super(Administrador.class);
    }
    
    public Administrador getByUsername(String username) {
        EntityManager entityManager = super.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Administrador administrador = null;
        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT e from Administrador e LEFT JOIN FETCH e.funcionario WHERE e.username = :user");
            query.setParameter("user", username);
            administrador = (Administrador)query.getSingleResult();
            transaction.commit();
        } catch (NoResultException n){
            administrador = new Administrador();
        }
        catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return administrador;
    }
}
