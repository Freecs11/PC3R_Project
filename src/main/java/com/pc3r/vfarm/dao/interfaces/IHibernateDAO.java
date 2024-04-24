package com.pc3r.vfarm.dao.interfaces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface IHibernateDAO<T> {

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    T findById(long id);

    Session getSession();

}
