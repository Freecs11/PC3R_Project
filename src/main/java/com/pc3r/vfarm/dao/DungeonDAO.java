package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Dungeon;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class DungeonDAO extends HibernateDAO<Dungeon>{
    public DungeonDAO() {
        super(Dungeon.class);
    }

    public  Dungeon createDungeon(String name, String type, Float posX, Float posY, String status){
        Dungeon dungeon = new Dungeon();
        dungeon.setName(name);
        dungeon.setType(type);
        dungeon.setLocalX(BigDecimal.valueOf(posX));
        dungeon.setLocalY(BigDecimal.valueOf(posY));
        dungeon.setStatus(status);
        dungeon.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        dungeon.setTime(new Timestamp(System.currentTimeMillis() + 86400000));
        save(dungeon);
        return dungeon;
    }

    public List getAllDungeons() {
        return getSession().createQuery("from Dungeon").list();
    }

    public Dungeon getDungeonById(String id) {
        return getSession().get(Dungeon.class, id);
    }

    public void updateDungeon(Dungeon dungeon) {
        update(dungeon);
    }
}
