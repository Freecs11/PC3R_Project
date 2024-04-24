package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Item;

public class ItemDAO extends HibernateDAO<Item>{
    public ItemDAO() {
        super(Item.class);
    }
}
