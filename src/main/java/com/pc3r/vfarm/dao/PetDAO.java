package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Pet;

public class PetDAO extends HibernateDAO<Pet>{
    public PetDAO() {
        super(Pet.class);
    }
}
