package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.User;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.Timestamp;
import java.time.Instant;

public class UserDAO extends HibernateDAO<User>{
    public UserDAO() {
        super(User.class);
    }


    public User getUserByUsername(String username) {
        return (User) getSession().createQuery("from User where username = :username").setParameter("username", username).uniqueResult();
    }

    public User getUserByEmail(String email) {
        String query = "from User where email = :email";
        return (User) getSession().createQuery(query).setParameter("email", email).uniqueResult();
    }


    public String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public User createUser(String username, String password, String email, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword(password));  // hash le password avant de le stocker
        user.setEmail(email);
        user.setRole(role);
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setPosition("user");
        user.setCoin(1000); // Initial coin balance
        save(user); // saves the user to the database
        return user;
    }


    public User getUserById(int userId) {
        return (User) getSession().createQuery("from User where id = :userId").setParameter("userId", userId).uniqueResult();
    }

    public void updateUser(User user) {
        update(user);
    }
}
