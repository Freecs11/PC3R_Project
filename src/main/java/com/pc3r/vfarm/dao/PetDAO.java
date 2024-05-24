package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Pet;

import java.util.List;

public class PetDAO extends HibernateDAO<Pet>{
    public PetDAO() {
        super(Pet.class);
    }


    public Pet getPetById(int petId) {
        return getSession().get(Pet.class, petId);
    }
}
