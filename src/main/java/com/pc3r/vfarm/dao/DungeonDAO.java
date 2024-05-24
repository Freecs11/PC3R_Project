package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Dungeon;

public class DungeonDAO extends HibernateDAO<Dungeon>{
    public DungeonDAO() {
        super(Dungeon.class);
    }

    public Dungeon getDungeonById(String id) {
        return getSession().get(Dungeon.class, id);
    }

    public void updateDungeon(Dungeon dungeon) {
        update(dungeon);
    }
}
