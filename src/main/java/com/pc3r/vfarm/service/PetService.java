package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.PetDAO;
import com.pc3r.vfarm.entities.Pet;

public class PetService extends GenericService<Pet> {
    public PetService() {
        super(new PetDAO());
    }
}
