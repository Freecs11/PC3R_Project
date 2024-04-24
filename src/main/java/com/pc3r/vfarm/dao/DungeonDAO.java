package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Dungeon;

public class DungeonDAO extends HibernateDAO<Dungeon>{
    public DungeonDAO() {
        super(Dungeon.class);
    }
}
