package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.User;

public class UserDAO extends HibernateDAO<User>{
    public UserDAO() {
        super(User.class);
    }



}
