package com.pc3r.vfarm.controller.collection;

import com.pc3r.vfarm.DTO.ResponseDTO;
import com.pc3r.vfarm.dao.PetDAO;
import com.pc3r.vfarm.dao.UserDAO;
import com.pc3r.vfarm.entities.Pet;
import com.pc3r.vfarm.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "marketServlet", value = "/api/v1/market")
public class MarketServlet extends HttpServlet {

    private PetDAO petDAO;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        petDAO = new PetDAO();
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            String buyerIdStr = request.getParameter("buyerId");
            String sellerIdStr = request.getParameter("sellerId");
            String petIdStr = request.getParameter("petId");

            if (petIdStr == null || (buyerIdStr == null && sellerIdStr == null)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write(new ResponseDTO("error", "Invalid request parameters").toJson());
                return;
            }

            Long petId = Long.parseLong(petIdStr);
            Pet pet = petDAO.findById(petId);
            if (pet == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write(new ResponseDTO("error", "Pet not found").toJson());
                return;
            }

            if (buyerIdStr != null) {
                handleBuyPet(request, response, pet, Long.parseLong(buyerIdStr));
            } else if (sellerIdStr != null) {
                handleSellPet(request, response, pet, sellerIdStr);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write(new ResponseDTO("error", "Invalid request parameters").toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write(new ResponseDTO("error", "Internal server error").toJson());
        }
    }

    private void handleBuyPet(HttpServletRequest request, HttpServletResponse response, Pet pet, Long buyerId) throws IOException {
        PrintWriter out = response.getWriter();

        User buyer = userDAO.findById(buyerId);
        if (buyer == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write(new ResponseDTO("error", "Buyer not found").toJson());
            return;
        }

        if (buyer.getCoin() < pet.getPrice()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write(new ResponseDTO("error", "Insufficient funds").toJson());
            return;
        }

        if (pet.getOwner() != null && pet.getOwner().getId().equals(buyerId)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write(new ResponseDTO("error", "You already own this pet").toJson());
            return;
        }

        buyer.setCoin(buyer.getCoin() - pet.getPrice());
        userDAO.update(buyer);
        pet.setOwner(buyer);
        petDAO.update(pet);

        out.write(new ResponseDTO("success", "Pet bought successfully").toJson());
    }

    private void handleSellPet(HttpServletRequest request, HttpServletResponse response, Pet pet, String sellerId) throws IOException {
        PrintWriter out = response.getWriter();
        Integer sellerIDInt = (Integer.parseInt(sellerId));
        User seller = userDAO.findById(sellerIDInt);
        if (seller == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write(new ResponseDTO("error", "Seller not found").toJson());
            return;
        }
        if (!Objects.equals(pet.getOwner().getId(), sellerIDInt)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write(new ResponseDTO("error", "You do not own this pet").toJson());
            return;
        }

        seller.setCoin(seller.getCoin() + pet.getPrice());
        pet.setOwner(null);

        userDAO.update(seller);
        petDAO.update(pet);

        out.write(new ResponseDTO("success", "Pet sold successfully").toJson());
    }
}
