package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.DungeonTrait;

public class DungeonTraitDAO extends HibernateDAO<DungeonTrait>{
    public DungeonTraitDAO() {
        super(DungeonTrait.class);
    }
}
