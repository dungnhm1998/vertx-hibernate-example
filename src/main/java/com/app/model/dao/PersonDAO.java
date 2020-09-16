/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model.dao;

import com.app.model.HibernateUtil;
import com.app.resources.entities.TblPerson;
import com.app.resources.entities.TblUser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class PersonDAO {

    public int add(TblPerson person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer id = person.getPersonId();
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(person);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }
    
}
