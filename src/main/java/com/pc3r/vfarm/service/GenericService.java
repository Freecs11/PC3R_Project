package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.interfaces.IHibernateDAO;

public abstract class GenericService<T>{
    protected final IHibernateDAO<T> dao;

    public GenericService(IHibernateDAO<T> dao) {
        this.dao = dao;
    }

    public void save(T entity) {
        dao.save(entity);
    }

    public void update(T entity) {
        dao.update(entity);
    }

    public void delete(T entity) {
        dao.delete(entity);
    }

    public T findById(long id) {
        return dao.findById(id);
    }
}
