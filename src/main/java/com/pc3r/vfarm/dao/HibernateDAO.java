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
        SessionFactory sessionFactory = getSessionFactory();
        Transaction tx = null;
        try (sessionFactory; Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(T entity) {
        SessionFactory sessionFactory = getSessionFactory();
        Transaction tx = null;
        try (sessionFactory; Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T entity) {
        SessionFactory sessionFactory = getSessionFactory();
        Transaction tx = null;
        try (sessionFactory; Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public T findById(long id) {
        SessionFactory sessionFactory = getSessionFactory();
        try (sessionFactory; Session session = sessionFactory.openSession()) {
            return session.get(clazz, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
