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
    private final PetDAO petDAO;  // Assuming you have a PetDAO to fetch user pets
    private final DungeonTraitDAO dungeonTraitDAO;
    public DungeonService() {
        super(new DungeonDAO());
        this.petDAO = new PetDAO();
        this.dungeonTraitDAO = new DungeonTraitDAO();
    }

    public ResponseDTO getDungeonInfo(String id) {
        // Logic to get dungeon info
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        // response in json format
        String response = "{\n" +
                "  \"id\": " + dungeon.getId() + ",\n" +
                "  \"name\": \"" + dungeon.getName() + "\",\n" +
                "  \"position_x\": " + dungeon.getLocalX() + ",\n" +
                "  \"position_y\": " + dungeon.getLocalY() + ",\n" +
                "  \"createdAt\": \"" + dungeon.getCreatedAt() + "\",\n" +
                "  \"status\": \"" + dungeon.getStatus() + "\"\n" +
                "}";
        return new ResponseDTO("success", response);
     }

    public ResponseDTO initiateFight(String id) {
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        if (!dungeon.getStatus().equals("idle")) {
            return new ResponseDTO("error", "Dungeon is not idle");
        }
        dungeon.setStatus("in fight");
        ((DungeonDAO) dao).updateDungeon(dungeon);
        return new ResponseDTO("success", "Fight initiated for dungeon id " + id);
    }

    public ResponseDTO resetDungeon(String id) {
        Dungeon dungeon =((DungeonDAO) dao).getDungeonById(id);
        dungeon.setStatus("idle");
        dungeon.setCombatDetails(null);
        dungeon.setSelectedItems(null);
        ((DungeonDAO) dao).updateDungeon(dungeon);
        return new ResponseDTO("success", "Dungeon id " + id + " has been reset");
    }

    public ResponseDTO selectItems(String id, String itemsJson) {
        List<Integer> itemIds = parseItemIdsFromJson(itemsJson);
        String itemIdsSerializedtoJSON = new Gson().toJson(itemIds);
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        dungeon.setSelectedItems(itemIdsSerializedtoJSON);
        ((DungeonDAO) dao).updateDungeon(dungeon);
        return new ResponseDTO("success", "Items selected for dungeon id " + id);
    }

    public ResponseDTO engageCombat(String id, String combatDetailsJson) {
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        dungeon.setCombatDetails(combatDetailsJson);
        ((DungeonDAO) dao).updateDungeon(dungeon);

        // fetch the combat details from the dungeon
        // it's a json string , { userId : 1 , selectedPets : [1,2,3] }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(combatDetailsJson, JsonObject.class);
        int userId = jsonObject.get("userId").getAsInt();
        JsonArray jsonArray = jsonObject.getAsJsonArray("selectedPets");

        // Fetch the pets of the user by their ids in the json array
        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            int petId = jsonArray.get(i).getAsInt();
            Pet pet = petDAO.getPetById(petId);
            pets.add(pet);
        }

        // Fetch the dungeon traits
        List<DungeonTrait> dungeonTraits = dungeonTraitDAO.getDungeonTraitsByDungeonId(id);

        // Calculate the total strength of the pets
        int totalPetStrength = pets.stream().mapToInt(Pet::getHealth).sum();

        // Calculate the total strength of the dungeon
        int totalDungeonStrength = dungeonTraits.stream().mapToInt(dungeonTrait -> dungeonTrait.getValue().intValue()).sum();

        // Determine combat outcome
        String combatResult;
        if (totalPetStrength > totalDungeonStrength) {
            combatResult = "Victory! Your pets defeated the dungeon.";

            dungeon.setStatus("idle");
            ((DungeonDAO) dao).updateDungeon(dungeon);

            // Award the user with some rewards
            // awardUser(userId, dungeon);

        } else {
            combatResult = "Defeat! Your pets were not strong enough.";
        }

        return new ResponseDTO("success", combatResult);
    }

    private List<Integer> parseItemIdsFromJson(String itemsJson) {
        // Parse the item IDs from the JSON string
        // JSON format: { "itemIds": [1, 2, 3] }
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