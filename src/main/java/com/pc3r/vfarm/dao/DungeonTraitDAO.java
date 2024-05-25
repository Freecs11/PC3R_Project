package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Dungeon;
import com.pc3r.vfarm.entities.DungeonTrait;

import java.math.BigDecimal;
import java.util.List;

public class DungeonTraitDAO extends HibernateDAO<DungeonTrait>{
    public DungeonTraitDAO() {
        super(DungeonTrait.class);
    }

    public List<DungeonTrait> getDungeonTraitsByDungeonId(Integer id) {
        String query = "from DungeonTrait where dungeon.id = :id";
        return getSession().createQuery(query).setParameter("id", id).list();
    }

    public void createDungeonTrait(String name, String description, Float value, Dungeon dungeon) {
        DungeonTrait dungeonTrait = new DungeonTrait();
        dungeonTrait.setName(name);
        dungeonTrait.setDescription(description);
        dungeonTrait.setValue(BigDecimal.valueOf(value));
        dungeonTrait.setDungeon(dungeon);
        save(dungeonTrait);
    }
}
