package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Log;

public class LogDAO extends HibernateDAO<Log>{
    public LogDAO() {
        super(Log.class);
    }
}
