package com.pc3r.vfarm.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pc3r.vfarm.DTO.ResponseDTO;
import com.pc3r.vfarm.dao.DungeonDAO;
import com.pc3r.vfarm.dao.DungeonTraitDAO;
import com.pc3r.vfarm.dao.PetDAO;
import com.pc3r.vfarm.entities.Dungeon;
import com.pc3r.vfarm.entities.DungeonTrait;
import com.pc3r.vfarm.entities.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonService extends GenericService<Dungeon> {
    private final PetDAO petDAO;
    private final DungeonTraitDAO dungeonTraitDAO;

    public DungeonService() {
        super(new DungeonDAO());
        this.petDAO = new PetDAO();
        this.dungeonTraitDAO = new DungeonTraitDAO();
    }

    public ResponseDTO getDungeonInfo(String id) {
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        Gson gson = new Gson();
        String response = gson.toJson(dungeon);
        return new ResponseDTO("success", response);
    }

    public ResponseDTO initiateFight(String id, int userId) {
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        if (!"idle".equals(dungeon.getStatus())) {
            return new ResponseDTO("error", "Dungeon is not idle");
        }
        if (dungeon.getUserFightingId() != null) {
            return new ResponseDTO("error", "Dungeon is already in use");
        }
        dungeon.setStatus("in fight");
        dungeon.setUserFightingId(userId);
        ((DungeonDAO) dao).updateDungeon(dungeon);
        return new ResponseDTO("success", "Fight initiated for dungeon id " + id);
    }

    public ResponseDTO resetDungeon(String id, int userId) {
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        if (!"in fight".equals(dungeon.getStatus()) || userId != dungeon.getUserFightingId()) {
            return new ResponseDTO("error", "Not authorized to reset the dungeon");
        }
        dungeon.setStatus("idle");
        dungeon.setCombatDetails(null);
        dungeon.setSelectedItems(null);
        dungeon.setUserFightingId(null);
        ((DungeonDAO) dao).updateDungeon(dungeon);
        return new ResponseDTO("success", "Dungeon id " + id + " has been reset");
    }

    public ResponseDTO selectItems(String id, String itemsJson, int userId) {
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        if (userId!= dungeon.getUserFightingId()) {
            return new ResponseDTO("error", "Not authorized to select items for this dungeon");
        }
        List<Integer> itemIds = parseItemIdsFromJson(itemsJson);
        String itemIdsSerializedtoJSON = new Gson().toJson(itemIds);
        dungeon.setSelectedItems(itemIdsSerializedtoJSON);
        ((DungeonDAO) dao).updateDungeon(dungeon);
        return new ResponseDTO("success", "Items selected for dungeon id " + id);
    }

    public ResponseDTO engageCombat(String id, String combatDetailsJson) {
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(combatDetailsJson, JsonObject.class);
        int userId = jsonObject.get("userId").getAsInt();
        JsonArray jsonArray = jsonObject.getAsJsonArray("selectedPets");

        if (userId!=dungeon.getUserFightingId()) {
            return new ResponseDTO("error", "Not authorized to engage combat for this dungeon");
        }

        dungeon.setCombatDetails(combatDetailsJson);
        ((DungeonDAO) dao).updateDungeon(dungeon);

        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            int petId = jsonArray.get(i).getAsInt();
            Pet pet = petDAO.getPetById(petId);
            pets.add(pet);
        }

        List<DungeonTrait> dungeonTraits = dungeonTraitDAO.getDungeonTraitsByDungeonId(id);

        int totalPetStrength = pets.stream().mapToInt(Pet::getHealth).sum();
        int totalDungeonStrength = dungeonTraits.stream().mapToInt(dungeonTrait -> dungeonTrait.getValue().intValue()).sum();

        String combatResult;
        if (totalPetStrength > totalDungeonStrength) {
            combatResult = "Victory! Your pets defeated the dungeon.";
            dungeon.setStatus("idle");
            dungeon.setUserFightingId(null);
            ((DungeonDAO) dao).updateDungeon(dungeon);
            // Award the user with some rewards
            // awardUser(userId, dungeon);
        } else {
            combatResult = "Defeat! Your pets were not strong enough.";
        }

        return new ResponseDTO("success", combatResult);
    }

    private List<Integer> parseItemIdsFromJson(String itemsJson) {
        List<Integer> itemIds = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(itemsJson, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("itemIds");

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                itemIds.add(jsonArray.get(i).getAsInt());
            }
        }

        return itemIds;
    }
}
