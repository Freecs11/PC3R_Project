package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.DungeonTraitDAO;
import com.pc3r.vfarm.entities.Dungeon;
import com.pc3r.vfarm.entities.DungeonTrait;

import java.util.List;

public class DungeonTraitService extends GenericService<DungeonTrait> {
    public DungeonTraitService() {
        super(new DungeonTraitDAO());
    }

    public List<DungeonTrait> getDungeonTraitsByDungeonId(Integer id) {
        return ((DungeonTraitDAO) dao).getDungeonTraitsByDungeonId(id);
    }

    public void createDungeonTrait(String temperature, String temperatureOfTheDungeon, float temp, Dungeon dungeon) {
        ((DungeonTraitDAO) dao).createDungeonTrait(temperature, temperatureOfTheDungeon, temp, dungeon);
    }
}
