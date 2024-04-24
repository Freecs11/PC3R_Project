package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.DungeonTraitDAO;
import com.pc3r.vfarm.entities.DungeonTrait;

public class DungeonTraitService extends GenericService<DungeonTrait> {
    public DungeonTraitService() {
        super(new DungeonTraitDAO());
    }
}
