package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.User;
import com.pc3r.vfarm.service.CoinService;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public User getUserByCoinId(String coinId) {
        String query = "from User where coinId = :coinId";
        return (User) getSession().createQuery(query).setParameter("coinId", coinId).uniqueResult();
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

        try (Session session = getSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(user);  // Save the user to generate the ID
                tx.commit();

                // After user is saved and ID is generated
                CoinService coinService = new CoinService();
                Integer coinId = coinService.generateCoinId(user.getId());
                user.setCoinId(coinId);
                session.beginTransaction();
                session.update(user);  // Update user to set Coin ID
                session.getTransaction().commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw e;  // Rethrow to be handled or logged by the caller
            }
        }

        return user;
    }





}
