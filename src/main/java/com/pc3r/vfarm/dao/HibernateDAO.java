package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.dao.interfaces.IHibernateDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static com.pc3r.vfarm.database.DatabaseConnection.getSessionFactory;

public class HibernateDAO<T> implements IHibernateDAO<T> {
    private Class<T> clazz;

    public HibernateDAO(Class<T> clazz) {
        this.clazz = clazz;
    }


    @Override
    public void save(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public T findById(long id) {
        Session session = getSessionFactory().openSession();
        T entity = session.get(clazz, id);
        session.close();
        return entity;
    }
}
