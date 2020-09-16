/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.app.model.HibernateUtil;
import com.app.model.dao.PersonDAO;
import com.app.model.dao.UserDAO;
import com.app.resources.entities.TblPerson;
import com.app.resources.entities.TblUser;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        HibernateUtil h = new HibernateUtil();
//        TblPerson person = new TblPerson("nguyen", "hoang dung", "manh", 22);
//        PersonDAO pSession = new PersonDAO();
//        System.out.println(pSession.add(person));

        TblUser user = new TblUser("usersix", "762313");
        UserDAO aSession = new UserDAO();
        if (aSession.login(user)) {
            System.out.println("ok");
        } else {
            System.out.println("ko");
        }
    }
}
