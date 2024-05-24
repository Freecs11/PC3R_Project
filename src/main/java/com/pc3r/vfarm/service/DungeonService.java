package com.pc3r.vfarm.service;

import com.pc3r.vfarm.DTO.ResponseDTO;
import com.pc3r.vfarm.dao.DungeonDAO;
import com.pc3r.vfarm.entities.Dungeon;

public class DungeonService extends GenericService<Dungeon> {
    public DungeonService() {
        super(new DungeonDAO());
    }

    public ResponseDTO getDungeonInfo(String id) {
        // Logic to get dungeon info
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        String response = "Dungeon id: " + dungeon.getId() + "\n" +
                "Dungeon name: " + dungeon.getName() + "\n" +
                "Dungeon position_x: " + dungeon.getLocalX() + "\n" +
                "Dungeon position_y: " + dungeon.getLocalY() + "\n" +
                "Dungeon createdAt: " + dungeon.getCreatedAt() + "\n"
                ;
        return new ResponseDTO("success", response);
     }

    public ResponseDTO initiateFight(String id) {
        // Logic to initiate fight
        return new ResponseDTO("success", "Fight initiated for dungeon id " + id);
    }

    public ResponseDTO resetDungeon(String id) {
        // Logic to reset dungeon
        return new ResponseDTO("success", "Dungeon id " + id + " has been reset");
    }

    public ResponseDTO selectItems(String id, String itemsJson) {
        // Logic to select items
        return new ResponseDTO("success", "Items selected for dungeon id " + id);
    }

    public ResponseDTO engageCombat(String id, String combatDetailsJson) {
        // Logic to engage in combat
        return new ResponseDTO("success", "Combat engaged for dungeon id " + id);
    }
}