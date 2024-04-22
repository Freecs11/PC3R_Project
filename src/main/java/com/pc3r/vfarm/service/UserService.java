package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.UserDAO;
import com.pc3r.vfarm.entities.User;

public class UserService extends GenericService<User>{
    public UserService() {
        super(new UserDAO());
    }


}
