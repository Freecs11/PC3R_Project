package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.DungeonTrait;

import java.util.List;

public class DungeonTraitDAO extends HibernateDAO<DungeonTrait>{
    public DungeonTraitDAO() {
        super(DungeonTrait.class);
    }

    public List<DungeonTrait> getDungeonTraitsByDungeonId(String id) {
        return getSession().createQuery("from DungeonTrait where dungeon.id = :id")
                .setParameter("id", id)
                .list();
    }
}
