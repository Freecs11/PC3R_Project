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

@WebServlet(name = "marketServlet", value = "/apii/v1/market")
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
        PrintWriter out = response.getWriter();

        try {
            String buyerIdStr = request.getParameter("buyerId");
            String sellerIdStr = request.getParameter("sellerId");
            String petIdStr = request.getParameter("petId");

            if (petIdStr == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new ResponseDTO("error", "Invalid URL").toJson());
                return;
            }

            Long petId = Long.parseLong(petIdStr);
            Pet pet = petDAO.findById(petId);
            if (pet == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new ResponseDTO("error", "Pet not found").toJson());
                return;
            }

            if (buyerIdStr != null) {
                Long buyerId = Long.parseLong(buyerIdStr);
                User buyer = userDAO.findById(buyerId);
                if (buyer == null) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write(new ResponseDTO("error", "Buyer not found").toJson());
                    return;
                }

                if (buyer.getCoin() < pet.getPrice()) {
                    response.getWriter().write(new ResponseDTO("error", "Insufficient funds").toJson());
                    return;
                }

                buyer.setCoin(buyer.getCoin() - pet.getPrice());
                pet.setOwner(buyer);

                userDAO.update(buyer);
                petDAO.update(pet);

                response.getWriter().write(new ResponseDTO("success", "Pet bought successfully").toJson());
            } else if (sellerIdStr != null) {
                Long sellerId = Long.parseLong(sellerIdStr);
                User seller = userDAO.findById(sellerId);
                if (seller == null) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write(new ResponseDTO("error", "Seller not found").toJson());
                    return;
                }

                seller.setCoin(seller.getCoin() + pet.getPrice());
                pet.setOwner(null);

                userDAO.update(seller);
                petDAO.update(pet);

                response.getWriter().write(new ResponseDTO("success", "Pet sold successfully").toJson());
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new ResponseDTO("error", "Invalid URLaaa").toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new ResponseDTO("error", "Invalid URL rr").toJson());
        }
    }
}
