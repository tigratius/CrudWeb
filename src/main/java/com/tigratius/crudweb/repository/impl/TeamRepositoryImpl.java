package com.tigratius.crudweb.repository.impl;

import com.tigratius.crudweb.entity.Team;
import com.tigratius.crudweb.repository.TeamRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TeamRepositoryImpl extends BaseRepository<Team> implements TeamRepository {

    public TeamRepositoryImpl(SessionFactory sessionFactory) {

        super.sessionFactory = sessionFactory;
    }

    public void delete(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Team team = session.get(Team.class, id);
            session.delete(team);
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

    public Team getById(Long id) {
        Session session = null;
        Team team = null;
        try {
            session = sessionFactory.openSession();
            team = session.get(Team.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return team;
    }

    public List<Team> getAll() {
        Session session = null;
        List<Team> teams = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            teams = session.createQuery("from Team", Team.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return teams;
    }
}
