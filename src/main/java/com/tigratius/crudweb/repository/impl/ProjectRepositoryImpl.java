package com.tigratius.crudweb.repository.impl;

import com.tigratius.crudweb.entity.Project;
import com.tigratius.crudweb.repository.ProjectRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl extends BaseRepository<Project> implements ProjectRepository {

    public ProjectRepositoryImpl(SessionFactory sessionFactory) {

        super.sessionFactory = sessionFactory;
    }

    public void delete(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Project project = session.get(Project.class, id);
            session.delete(project);
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

    public Project getById(Long id) {
        Session session = null;
        Project project = null;
        try {
            session = sessionFactory.openSession();
            project = session.get(Project.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return project;
    }

    public List<Project> getAll() {
        Session session = null;
        List<Project> projects = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            projects = session.createQuery("from Project", Project.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return projects;
    }
}
