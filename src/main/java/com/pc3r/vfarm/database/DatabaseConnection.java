package com.pc3r.vfarm.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {
    private static SessionFactory factory;

    private static void configureFactory()
    {
        try {
            factory = new Configuration()
                    .addAnnotatedClass(com.pc3r.vfarm.entities.Coin.class)
                    .addAnnotatedClass(com.pc3r.vfarm.entities.Dungeon.class)
                    .addAnnotatedClass(com.pc3r.vfarm.entities.DungeonTrait.class)
                    .addAnnotatedClass(com.pc3r.vfarm.entities.Item.class)
                    .addAnnotatedClass(com.pc3r.vfarm.entities.Log.class)
                    .addAnnotatedClass(com.pc3r.vfarm.entities.Pet.class)
                    .addAnnotatedClass(com.pc3r.vfarm.entities.PetTrait.class)
                    .addAnnotatedClass(com.pc3r.vfarm.entities.Reward.class)
                    .addAnnotatedClass(com.pc3r.vfarm.entities.User.class)
                    .configure("hibernate.cfg.xml")
                    .configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static org.hibernate.SessionFactory getSessionFactory() {
        if (factory == null) {
            configureFactory();
        }

        return factory;
    }
}