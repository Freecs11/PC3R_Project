package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Reward;

import java.util.List;

public class RewardDAO extends HibernateDAO<Reward>{
    public RewardDAO() {
        super(Reward.class);
    }

    public Reward getRewardByDungeonId(Integer id) {
        String hql = "FROM Reward WHERE \"dungeonid\" = :id";
        return (Reward) getSession().createQuery(hql).setParameter("id", id).uniqueResult();
    }

    public List<Reward> getRewardsByDungeonId(Integer id) {
        String hql = "FROM Reward WHERE \"dungeonid\" = :id";
        return getSession().createQuery(hql).setParameter("id", id).list();
    }
}
