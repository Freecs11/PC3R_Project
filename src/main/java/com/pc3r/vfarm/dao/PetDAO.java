package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Pet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PetDAO extends HibernateDAO<Pet>{
    public PetDAO() {
        super(Pet.class);
    }


    public Pet getPetById(int petId) {
        return getSession().get(Pet.class, petId);
    }

    public void deletePet(Pet pet) {
        getSession().delete(pet);
    }

    public List<Pet> getPetsByOwnerId(int ownerId) {
        return getSession().createQuery("from Pet where owner.id = :ownerId", Pet.class)
                .setParameter("ownerId", ownerId)
                .getResultList();
    }

    public void savePet(Pet pet) {
        getSession().save(pet);
    }
}
