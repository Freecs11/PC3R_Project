package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.ItemDAO;
import com.pc3r.vfarm.entities.Item;

public class ItemService extends GenericService<Item> {
    public ItemService() {
        super(new ItemDAO());
    }

    public Item getItemById(Integer itemId) {
        return ((ItemDAO) dao).getItemById(itemId);
    }
}
