package com.pc3r.vfarm.controller.collection;

import com.pc3r.vfarm.dao.PetDAO;
import com.pc3r.vfarm.entities.Pet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "petServlet", value = "/apii/v1/pet")
public class PetServlet extends HttpServlet {
    private final PetDAO petDAO = new PetDAO();

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

        // on recupere l'utilisateur

        // on cree un nouvel animal
        Pet pet = new Pet();
        pet.setName(name);
        pet.setType(type);
        pet.setHealth(Integer.parseInt(health));

        // on sauvegarde l'animal
        petDAO.SavePet(pet);


        out.println("{\"status\": \"success\"}");


    }
}
