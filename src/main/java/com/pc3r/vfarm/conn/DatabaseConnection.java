package com.pc3r.vfarm.conn;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
            catch (Exception e){
                System.out.println("DB init failed");
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}