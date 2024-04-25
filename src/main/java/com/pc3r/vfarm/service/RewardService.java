package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.RewardDAO;
import com.pc3r.vfarm.entities.Reward;

public class RewardService extends GenericService<Reward> {
    public RewardService() {
        super(new RewardDAO());
    }
}
