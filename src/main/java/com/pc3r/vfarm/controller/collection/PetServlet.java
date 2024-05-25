package com.pc3r.vfarm.controller.collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pc3r.vfarm.DTO.ResponseDTO;
import com.pc3r.vfarm.entities.Pet;
import com.pc3r.vfarm.service.PetService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "petServlet", value = "/apii/v1/pet")
public class PetServlet extends HttpServlet {
    private static final PetService petService = new PetService();

    public PetServlet() {
    }

    public void init() {
    }

    public void destroy() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        // on recupere les parametres de la requete
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String health = request.getParameter("health");
        String price = request.getParameter("price");

        // on cree un nouvel animal
        Pet pet = new Pet();
        pet.setName(name);
        pet.setType(type);
        pet.setHealth(Integer.parseInt(health));
        pet.setCreatedAt(Timestamp.from(java.time.Instant.now()));
        pet.setPurchasedAt(Timestamp.from(java.time.Instant.now()));
        pet.setPrice(Integer.parseInt(price));
        // on sauvegarde l'animal
        petService.save(pet);
        response.getWriter().write(new ResponseDTO("success", "Pet created successfully").toJson());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String ownerIdStr = request.getParameter("ownerId");
        String allPets = request.getParameter("all");

        try {
            if (id != null) {
                // Si le paramètre "id" est présent, récupérer un animal spécifique
                Pet pet = petService.getPetById(Integer.parseInt(id));
                if (pet != null) {
                    JsonObject petJson = new JsonObject();
                    petJson.addProperty("id", pet.getId());
                    petJson.addProperty("name", pet.getName());
                    petJson.addProperty("type", pet.getType());
                    response.getWriter().write(petJson.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(new ResponseDTO("error", "Pet not found").toJson());
                }
            } else if (ownerIdStr != null) {
                // Si le paramètre "ownerId" est présent, récupérer tous les animaux pour cet utilisateur
                Integer ownerId = Integer.parseInt(ownerIdStr);
                List<Pet> pets = petService.getPetsByOwnerId(ownerId);


                if (!pets.isEmpty()) {
                    JsonArray petsJson = new JsonArray();
                    for (Pet pet : pets) {
                        JsonObject petJson = new JsonObject();
                        petJson.addProperty("id", pet.getId());
                        petJson.addProperty("name", pet.getName());
                        petJson.addProperty("type", pet.getType());
                        petsJson.add(petJson);
                    }
                    response.getWriter().write(petsJson.toString());

                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(new ResponseDTO("error", "No pets found for the given owner ID " + ownerId ).toJson());
                }
            } else if (allPets != null) {
                // Si le paramètre "all" est présent, récupérer tous les animaux
                List<Pet> pets = petService.getAllPets();
                if (!pets.isEmpty()) {
                    JsonArray petsJson = new JsonArray();
                    for (Pet pet : pets) {
                        JsonObject petJson = new JsonObject();
                        petJson.addProperty("id", pet.getId());
                        petJson.addProperty("name", pet.getName());
                        petJson.addProperty("type", pet.getType());
                        petsJson.add(petJson);
                    }
                    response.getWriter().write(petsJson.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(new ResponseDTO("error", "No pets found").toJson());
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new ResponseDTO("error", "Missing required parameters").toJson());
            }

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"status\": \"error\", \"message\": \"Invalid ID format\"}");
        }
    }

    // deleate
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        try {
            if (id != null) {
                // Si le paramètre "id" est présent, supprimer un animal spécifique
                Pet pet = petService.getPetById(Integer.parseInt(id));
                if (pet != null) {
                    petService.deletePet(pet);
                    response.getWriter().write(new ResponseDTO("success", "Pet deleted successfully").toJson());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(new ResponseDTO("error", "Pet not found").toJson());
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new ResponseDTO("error", "Missing required parameters").toJson());
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new ResponseDTO("error", "Invalid ID format").toJson());
        }
    }

    // update
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String health = request.getParameter("health");

        try {
            if (id != null) {
                // Si le paramètre "id" est présent, mettre à jour un animal spécifique
                Pet pet = petService.getPetById(Integer.parseInt(id));
                if (pet != null) {
                    pet.setName(name);
                    pet.setType(type);
                    pet.setHealth(Integer.parseInt(health));
                    petService.updatePet(pet);
                    response.getWriter().write(new ResponseDTO("success", "Pet updated successfully").toJson());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(new ResponseDTO("error", "Pet not found").toJson());
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new ResponseDTO("error", "Missing required parameters").toJson());
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new ResponseDTO("error", "Invalid ID format").toJson());
        }
    }
}
