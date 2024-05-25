package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.RewardDAO;
import com.pc3r.vfarm.entities.Reward;

import java.util.List;

public class RewardService extends GenericService<Reward> {
    public RewardService() {
        super(new RewardDAO());
    }

    // gets one reward by dungeon id if it exists
    public Reward getRewardByDungeonId(Integer id) {
        return ((RewardDAO) dao).getRewardByDungeonId(id);
    }

    // gets all rewards by dungeon id if they exist
    public List<Reward> getRewardsByDungeonId(Integer id) {
        return ((RewardDAO) dao).getRewardsByDungeonId(id);
    }
}
