package com.tigratius.crudweb.repository.impl;

import com.tigratius.crudweb.entity.User;
import com.tigratius.crudweb.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl extends BaseRepository<User> implements UserRepository {

    public UserRepositoryImpl(SessionFactory sessionFactory) {

        super.sessionFactory = sessionFactory;
    }

    public void delete(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public User getById(Long id) {
        Session session = null;
        User skill = null;
        try {
            session = sessionFactory.openSession();
            skill = session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

    public List<User> getAll() {
        Session session = null;
        List<User> users = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            users = session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }
}
