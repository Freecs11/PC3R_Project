package com.pc3r.vfarm.controller.collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pc3r.vfarm.dao.PetDAO;
import com.pc3r.vfarm.entities.Pet;
import com.pc3r.vfarm.service.PetService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.plugins.jpeg.JPEGQTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "petServlet", value = "/apii/v1/pet")
public class PetServlet extends HttpServlet {
    private static final PetService petService = new PetService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PetServlet() {
        // Enregistrer le module JavaTimeModule pour prendre en charge java.time.Instant
        objectMapper.registerModule(new JavaTimeModule());
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

        // on cree un nouvel animal
        Pet pet = new Pet();
        pet.setName(name);
        pet.setType(type);
        pet.setHealth(Integer.parseInt(health));
        pet.setCreatedAt(java.time.Instant.now());
        pet.setPurchasedAt(java.time.Instant.now());
        // on sauvegarde l'animal
        petService.save(pet);

        out.println("{\"status\": \"success\", \"pet\": " + objectMapper.writeValueAsString(pet) + "}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String ownerIdStr = request.getParameter("ownerId");

        try {
            if (id != null) {
                // Si le paramètre "id" est présent, récupérer un animal spécifique
                Pet pet = petService.getPetById(Integer.parseInt(id));
                if (pet != null) {
                    String petJson = objectMapper.writeValueAsString(pet);
                    out.println(petJson);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("{\"status\": \"error\", \"message\": \"Pet not found\"}");
                }
            } else if (ownerIdStr != null) {
                // Si le paramètre "ownerId" est présent, récupérer tous les animaux pour cet utilisateur
                Integer ownerId = Integer.parseInt(ownerIdStr);
                List<Pet> pets = petService.getPetsByOwnerId(ownerId);

                List<PetDTO> petDTOs = pets.stream()
                        .map(p -> new PetDTO(p.getId(), p.getName()))
                        .collect(Collectors.toList());
                if (!petDTOs.isEmpty()) {
                    String petsJson = objectMapper.writeValueAsString(petDTOs);
                    out.println(petsJson);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("{\"status\": \"error\", \"message\": \"No pets found for the given owner ID\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"status\": \"error\", \"message\": \"Missing required parameters\"}");
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
                    out.println("{\"status\": \"success\", \"message\": \"Pet deleted successfully\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("{\"status\": \"error\", \"message\": \"Pet not found\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"status\": \"error\", \"message\": \"Missing required parameters\"}");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"status\": \"error\", \"message\": \"Invalid ID format\"}");
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
                    out.println("{\"status\": \"success\", \"message\": \"Pet updated successfully\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("{\"status\": \"error\", \"message\": \"Pet not found\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"status\": \"error\", \"message\": \"Missing required parameters\"}");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"status\": \"error\", \"message\": \"Invalid ID format\"}");
        }
    }
}

class PetDTO {
    private int id;
    private String name;

    public PetDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
