package com.pc3r.vfarm.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.pc3r.vfarm.DTO.ResponseDTO;
import com.pc3r.vfarm.DTO.RewardDto;
import com.pc3r.vfarm.dao.DungeonDAO;
import com.pc3r.vfarm.dao.DungeonTraitDAO;
import com.pc3r.vfarm.dao.PetDAO;
import com.pc3r.vfarm.dao.RewardDAO;
import com.pc3r.vfarm.entities.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Logger;

public class DungeonService extends GenericService<Dungeon> {
    private final PetDAO petDAO;
    private final DungeonTraitService dungeonTraitService;
    private final RewardService rewardService;
    private final UserService userService;
    private final ItemService itemService;
    private final WeatherService weatherService;
    private final Logger logger = Logger.getLogger(DungeonService.class.getName());


    public DungeonService() {
        super(new DungeonDAO());
        this.petDAO = new PetDAO();
        this.dungeonTraitService = new DungeonTraitService();
        this.rewardService = new RewardService();
        this.userService = new UserService();
        this.itemService = new ItemService();
        this.weatherService = new WeatherService();
    }


    public ResponseDTO getAllDungeons(float posX, float posY) throws IOException {

        List<Dungeon> dungeons = ((DungeonDAO) dao).getAllDungeons();
        dungeons.sort(
                Comparator.comparing(Dungeon::getCreatedAt).reversed()
        );
        // Generate a new dungeon if the last one is older than 24 hours
        Dungeon newestDungeon = dungeons.get(0);
        Timestamp createdAt = newestDungeon.getCreatedAt();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long diff = now.getTime() - createdAt.getTime();
        // 1 day = 86400000 milliseconds
        if (diff > 86400000) {
            // Generate dungeons with Weather API
            List<Float> weatherData = weatherService.getWeather(posX, posY);
            float temp = weatherData.get(0);
            float wind = weatherData.get(1);
            float humidity = weatherData.get(2);
            float pressure = weatherData.get(3);
            int randomeDungeons = (int) (Math.random() * 5 + 1);
            for (int i = 0; i < randomeDungeons; i++){
                float posXRandomized = posX + (float) (Math.random() * 0.025);
                float posYRandomized = posY + (float) (Math.random() * 0.025);
                Dungeon dungeon = ((DungeonDAO) dao).createDungeon(weatherService.getCity(posX, posY), "city", posXRandomized, posYRandomized, "idle");
                dungeonTraitService.createDungeonTrait("Temperature", "Temperature of the dungeon", temp, dungeon);
                dungeonTraitService.createDungeonTrait("Wind", "Wind speed of the dungeon", wind, dungeon);
                dungeonTraitService.createDungeonTrait("Humidity", "Humidity of the dungeon", humidity, dungeon);
                dungeonTraitService.createDungeonTrait("Pressure", "Pressure of the dungeon", pressure, dungeon);
                dungeons.add(dungeon);
            }
        }
        // Delete dungeons that are older than their time
        List<Dungeon> dungeonsToDelete = new ArrayList<>();
        for (Dungeon dungeon : dungeons) {
            Timestamp dungeonTime = dungeon.getTime();
            long timeDiff = now.getTime() - dungeonTime.getTime();
            if (timeDiff > 0) {
                dungeonsToDelete.add(dungeon);
            }
        }
        // Delete the DungeonTraits that are associated with the dungeons to be deleted
        for (Dungeon dungeon : dungeonsToDelete) {
            List<DungeonTrait> dungeonTraits = dungeonTraitService.getDungeonTraitsByDungeonId(dungeon.getId());
            for (DungeonTrait dungeonTrait : dungeonTraits) {
                dungeonTraitService.delete(dungeonTrait);

            dao.delete(dungeon);            }
    }
        for (Dungeon dungeon : dungeonsToDelete) {
            dungeons.remove(dungeon);
        }
        Gson gson = new Gson();
        String response = gson.toJson(dungeons);
        return new ResponseDTO("success", response);
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
        if (!"in fight".equals(dungeon.getStatus()) || dungeon.getUserFightingId() == null || dungeon.getUserFightingId() != userId) {
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
        if (dungeon.getUserFightingId() == null || dungeon.getUserFightingId() != userId) {
            return new ResponseDTO("error", "Not authorized to select items for this dungeon");
        }
        List<Integer> itemIds = parseItemIdsFromJson(itemsJson);
        String itemIdsSerializedtoJSON = new Gson().toJson(itemIds);
        dungeon.setSelectedItems(itemIdsSerializedtoJSON);
        ((DungeonDAO) dao).updateDungeon(dungeon);
        return new ResponseDTO("success", "Items selected for dungeon id " + id + " are " + itemIdsSerializedtoJSON);
    }

    public ResponseDTO engageCombat(String id, String combatDetailsJson) {
        Dungeon dungeon = ((DungeonDAO) dao).getDungeonById(id);
        if (dungeon == null) {
            return new ResponseDTO("error", "Dungeon not found");
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(combatDetailsJson, JsonObject.class);
        int userId = jsonObject.get("userId").getAsInt();
        JsonArray jsonArray = jsonObject.getAsJsonArray("selectedPets");

        if (dungeon.getUserFightingId() == null || dungeon.getUserFightingId() != userId) {
            return new ResponseDTO("error", "Not authorized to engage combat for this dungeon");
        }

        dungeon.setCombatDetails(combatDetailsJson);
        ((DungeonDAO) dao).updateDungeon(dungeon);

        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            int petId = jsonArray.get(i).getAsInt();
            Pet pet = petDAO.getPetById(petId);
            if (pet != null) {
                pets.add(pet);
            }
        }

        List<DungeonTrait> dungeonTraits = dungeonTraitService.getDungeonTraitsByDungeonId(dungeon.getId());

        // It's stored as follows: [1, 2, 3, 4, 5] but in string format
        List<Integer> selectedItems = dungeon.getSelectedItems() != null ? parseItemIds(dungeon.getSelectedItems()) : new ArrayList<>();

        int totalPetStrength = pets.stream()
                .filter(Objects::nonNull) // Ensures no null values are processed
                .mapToInt(Pet::getHealth)
                .sum();

        int totalDungeonStrength = dungeonTraits.stream()
                .filter(Objects::nonNull)
                .mapToInt(dungeonTrait -> dungeonTrait.getValue().intValue())
                .sum();

        // Add the strength of the selected items to the total pets strength
        for (Integer itemId : selectedItems) {
            Item item = itemService.getItemById(itemId);
            if (item != null) {
                totalPetStrength += item.getValue().intValue();
            }
        }

        // Log the computed strengths for debugging
        logger.info("Total Pet Strength: " + totalPetStrength);
        logger.info("Total Dungeon Strength: " + totalDungeonStrength);

        String combatResult;
        if (totalPetStrength > totalDungeonStrength) {
            combatResult = "Victory! Your pets defeated the dungeon. The pets' strength is " + totalPetStrength + " and the dungeon's strength is " + totalDungeonStrength;
            dungeon.setStatus("idle");
            dungeon.setUserFightingId(null);
            ((DungeonDAO) dao).updateDungeon(dungeon);
            // Award the user with some rewards
            awardUser(userId, dungeon);
        } else {
            combatResult = "Defeat! Your pets were not strong enough. The pets' strength is " + totalPetStrength + " and the dungeon's strength is " + totalDungeonStrength;
        }

        return new ResponseDTO("success", combatResult);
    }
    private void awardUser(int userId, Dungeon dungeon) {
        List<Reward> rewards = rewardService.getRewardsByDungeonId(dungeon.getId());
        if (rewards == null || rewards.isEmpty()) {
            return;
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            return;
        }

        Integer totalReward = rewards.stream()
                .map(Reward::getAmount)
                .map(BigDecimal::intValue)
                .reduce(0, Integer::sum);

        user.setCoin(user.getCoin() + totalReward);
        userService.updateUser(user);

    }

    // stock it as follows: [1, 2, 3, 4, 5] in string format so just parse it
    private List<Integer> parseItemIds(String itemsJson) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
        List<Integer> itemIds = gson.fromJson(itemsJson, listType);
        return itemIds;
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
