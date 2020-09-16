/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model.dao;

import com.app.model.HibernateUtil;
import com.app.resources.entities.TblUser;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Admin
 */
public class UserDAO {

    public int add(TblUser user) {
        System.out.println("add user: " + user.getUsername());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer userID = null;
        try {
            transaction = session.beginTransaction();
            userID = (Integer) session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userID;
    }

    public boolean login(TblUser user) {
        boolean rs = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
//        Integer rs = null;
        try {
            transaction = session.beginTransaction();
            //---  
            Criteria cr = session.createCriteria(TblUser.class);
            cr.add(Restrictions.eq("username", user.getUsername()));
            List results = cr.list();
            System.out.println("-------------------------------------\nresult = " + results.get(0));
            //---
            if (results.isEmpty()) {
                rs = false;
            } else {
                TblUser u = (TblUser) results.get(0);
                if (u.getPassword().equals(user.getPassword())) {
                    rs = true;
                }
                System.out.println("+++" + u.getUsername());
            }
            //rs = (Integer) session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return rs;
    }

    public void changePassword(TblUser user, String newPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
//        Integer rs = null;
        try {
            transaction = session.beginTransaction();

            TblUser m = (TblUser) session.get(TblUser.class, user.getId());
            m.setPassword(newPassword);
            //rs = (Integer) session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
