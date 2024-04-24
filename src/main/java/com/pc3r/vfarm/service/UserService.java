package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.UserDAO;
import com.pc3r.vfarm.entities.User;

public class UserService extends GenericService<User>{
    public UserService() {
        super(new UserDAO());
    }

    public User getUserByUsername(String username) {
        return ((UserDAO) dao).getUserByUsername(username);
    }

    public User getUserByEmail(String email) {
        return ((UserDAO) dao).getUserByEmail(email);
    }

    public User getUserByCoinId(String coinId) {
        return ((UserDAO) dao).getUserByCoinId(coinId);
    }

    public User createUser(String username, String password, String email, String role) {
        return ((UserDAO) dao).createUser(username, password, email, role);
    }


}
