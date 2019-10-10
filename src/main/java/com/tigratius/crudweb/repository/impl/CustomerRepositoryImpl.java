package com.tigratius.crudweb.repository.impl;

import com.tigratius.crudweb.entity.Customer;
import com.tigratius.crudweb.repository.CustomerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl extends BaseRepository<Customer> implements CustomerRepository{

    public CustomerRepositoryImpl(SessionFactory sessionFactory) {
        super.sessionFactory = sessionFactory;
    }

    public void delete(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
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

    public Customer getById(Long id) {
        Session session = null;
        Customer customer = null;
        try {
            session = sessionFactory.openSession();
            customer = session.get(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return customer;
    }

    public List<Customer> getAll() {
        Session session = null;
        List<Customer> customers = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            customers = session.createQuery("from Customer", Customer.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return customers;
    }
}
