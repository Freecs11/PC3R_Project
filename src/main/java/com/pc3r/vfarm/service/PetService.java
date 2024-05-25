package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.PetDAO;
import com.pc3r.vfarm.entities.Pet;
import java.util.List;

public class PetService extends GenericService<Pet> {
    public PetService() {
        super(new PetDAO());
    }
    public Pet getPetById(int id) {
        return ((PetDAO) dao).getPetById(id);
    }

    public void updatePet(Pet pet) {
        update(pet);
    }

    public void deletePet(Pet pet) {
        ((PetDAO) dao).deletePet(pet);
    }

    public void savePet(Pet pet) {
        ((PetDAO) dao).savePet(pet);
    }

    public List<Pet> getPetsByOwnerId(int ownerId) {
        return ((PetDAO) dao).getPetsByOwnerId(ownerId);
    }
}
