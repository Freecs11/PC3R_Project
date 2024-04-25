package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Reward;

public class RewardDAO extends HibernateDAO<Reward>{
    public RewardDAO() {
        super(Reward.class);
    }

}
