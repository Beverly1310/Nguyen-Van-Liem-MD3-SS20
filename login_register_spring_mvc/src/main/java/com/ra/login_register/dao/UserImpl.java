package com.ra.login_register.dao;

import com.ra.login_register.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserImpl implements IUser{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Boolean save(Users users) {
        Session session = sessionFactory.openSession();
        try {
            List list = session.createQuery(" from Users where username=:username and password=:password", Users.class).
                    setParameter("username",users.getUsername()).
                    setParameter("password",users.getPassword()).list();
            if (list.isEmpty()){
            session.beginTransaction();
            session.save(users);
            session.getTransaction().commit();
            return true;}
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean login(Users users) {
       Session session = sessionFactory.openSession();
       try {
           List list = session.createQuery(" from Users where username=:username and password=:password", Users.class).
                   setParameter("username",users.getUsername()).
                   setParameter("password",users.getPassword()).list();
           if (!list.isEmpty()) {
               return true;
           }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           session.close();
       }
       return false;
    }

    @Override
    public Users findUserbyUsername(String username) {
        Session session = sessionFactory.openSession();
        try {
            Users users = session.createQuery("from Users where username=:username", Users.class).setParameter("username",username).getSingleResult();
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }


}
