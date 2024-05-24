package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Dungeon;

public class DungeonDAO extends HibernateDAO<Dungeon>{
    public DungeonDAO() {
        super(Dungeon.class);
    }

    public Dungeon getDungeonById(String id) {
        String query = "from Dungeon where id = :id";
        return (Dungeon) getSession().createQuery(query).setParameter("id", id).uniqueResult();
    }

}
