package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.PetTrait;

public class PetTraitDAO extends HibernateDAO<PetTrait>{
    public PetTraitDAO() {
        super(PetTrait.class);
    }
}
