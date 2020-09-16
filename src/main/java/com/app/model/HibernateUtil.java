/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import com.app.resources.entities.TblPerson;
import com.app.resources.entities.TblUser;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Admin
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties setting = new Properties();
                setting.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                setting.put(Environment.URL, "jdbc:sqlserver://localhost\\DESKTOP-FB0DNKO\\SQLEXPRESS:1433;databaseName=Users");
                setting.put(Environment.USER, "qlks");
                setting.put(Environment.PASS, "qlks");
                setting.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
                setting.put(Environment.SHOW_SQL, "true");
                setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                //setting.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(setting);
                configuration.addAnnotatedClass(TblUser.class);
                configuration.addAnnotatedClass(TblPerson.class);
                StandardServiceRegistryBuilder builder
                        = new StandardServiceRegistryBuilder();
                builder.applySettings(configuration.getProperties());
                StandardServiceRegistry serviceRegistry = builder.build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }
    //session

}
