package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Pet;

public class PetDAO extends HibernateDAO<Pet>{
    public PetDAO() {
        super(Pet.class);
    }

    public Pet getPetById(int id) {
        return (Pet) getSession().createQuery("from Pet where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    public void SavePet(Pet pet) {
        System.out.println(pet.toString());
        save(pet);
    }


}
