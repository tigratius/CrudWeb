package com.tigratius.crudweb.repository.impl;

import com.tigratius.crudweb.entity.Skill;
import com.tigratius.crudweb.repository.SkillRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class SkillRepositoryImpl extends BaseRepository<Skill> implements SkillRepository {

    public SkillRepositoryImpl(SessionFactory sessionFactory) {

        super.sessionFactory = sessionFactory;
    }

    public void delete(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Skill skill = session.get(Skill.class, id);
            session.delete(skill);
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

    public Skill getById(Long id) {
        Session session = null;
        Skill skill = null;
        try {
            session = sessionFactory.openSession();
            skill = session.get(Skill.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

    public List<Skill> getAll() {
        Session session = null;
        List<Skill> skills = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            skills = session.createQuery("from Skill", Skill.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skills;
    }
}
