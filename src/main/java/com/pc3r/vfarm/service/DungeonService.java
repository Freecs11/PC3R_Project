package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.DungeonDAO;
import com.pc3r.vfarm.entities.Dungeon;

public class DungeonService extends GenericService<Dungeon> {
    public DungeonService() {
        super(new DungeonDAO());
    }
}