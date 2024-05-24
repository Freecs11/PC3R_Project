package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.User;
import com.pc3r.vfarm.service.CoinService;
import org.mindrot.jbcrypt.BCrypt;


import java.time.Instant;

public class UserDAO extends HibernateDAO<User>{
    public UserDAO() {
        super(User.class);
    }


    public User getUserByUsername(String username) {
        String query = "from User where username = :username";
        return (User) getSession().createQuery(query).setParameter("username", username).uniqueResult();
    }

    public User getUserByEmail(String email) {
        String query = "from User where email = :email";
        return (User) getSession().createQuery(query).setParameter("email", email).uniqueResult();
    }


    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }


    public User createUser(String username, String password, String email, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword(password));  // Ensuring password is hashed before saving
        user.setEmail(email);
        user.setRole(role);
        user.setCreatedAt(Instant.now());
        user.setPosition("user");
        save(user);  // This method should be responsible for persisting the user entity
        return user;
    }




}
