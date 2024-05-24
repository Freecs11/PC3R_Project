package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Pet;

import java.util.List;

public class PetDAO extends HibernateDAO<Pet>{
    public PetDAO() {
        super(Pet.class);
    }

    public Pet getPetById(int id) {
        return (Pet) getSession().createQuery("from Pet where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    //getPetsByOwnerId
    public List getPetsByOwnerId(int ownerId) {
        return getSession().createQuery("from Pet where owner.id = :ownerId")
                .setParameter("ownerId", ownerId)
                .list();
    }

//    save
    public void savePet(Pet pet) {
        save(pet);
    }

    // delete
    public void deletePet(Pet pet) {
        delete(pet);
    }
}
