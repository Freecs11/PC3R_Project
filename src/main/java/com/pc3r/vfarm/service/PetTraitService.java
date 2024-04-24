package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.PetTraitDAO;
import com.pc3r.vfarm.entities.PetTrait;

public class PetTraitService extends GenericService<PetTrait> {
    public PetTraitService() {
        super(new PetTraitDAO());
    }
}
