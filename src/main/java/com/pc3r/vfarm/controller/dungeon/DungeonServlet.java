package com.pc3r.vfarm.controller.dungeon;


import com.pc3r.vfarm.DTO.ResponseDTO;
import com.pc3r.vfarm.service.DungeonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;
@WebServlet(name = "dungeonServlet", urlPatterns = {"/spi/v1/DungeonService/*"})
public class DungeonServlet extends HttpServlet {
    private DungeonService dungeonService;

    @Override
    public void init() {
        this.dungeonService = new DungeonService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        if (pathParts.length < 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new ResponseDTO("error", "Invalid URL").toJson());
            return;
        }

        // id
        String id = pathParts[1];

        if (pathParts.length == 2) {
            // vpi/v1/DungeonService/{id}
            handleGetDungeon(response, id);
        } else if (pathParts.length == 3 && "fight".equals(pathParts[2])) {
            // vpi/v1/DungeonService/{id}/fight
            handleFight(response, id);
        } else if (pathParts.length == 3 && "reset".equals(pathParts[2])) {
            // vpi/v1/DungeonService/{id}/reset
            handleReset(response, id);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new ResponseDTO("error", "Invalid URL").toJson());
        }
    }

    private void handleGetDungeon(HttpServletResponse response, String id) throws IOException {
        ResponseDTO responseDTO = dungeonService.getDungeonInfo(id);
        response.getWriter().write(responseDTO.toJson());
    }

    private void handleFight(HttpServletResponse response, String id) throws IOException {
        ResponseDTO responseDTO = dungeonService.initiateFight(id);
        response.getWriter().write(responseDTO.toJson());
    }

    private void handleReset(HttpServletResponse response, String id) throws IOException {
        ResponseDTO responseDTO = dungeonService.resetDungeon(id);
        response.getWriter().write(responseDTO.toJson());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        if (pathParts.length < 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new ResponseDTO("error", "Invalid URL").toJson());
            return;
        }

        String id = pathParts[1];

        if (pathParts.length == 3 && "SelectedItems".equals(pathParts[2])) {
            handleSelectedItems(request, response, id);
        } else if (pathParts.length == 3 && "combat".equals(pathParts[2])) {
            handleCombat(request, response, id);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new ResponseDTO("error", "Invalid URL").toJson());
        }
    }

    private void handleSelectedItems(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
        String itemsJson = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        ResponseDTO responseDTO = dungeonService.selectItems(id, itemsJson);
        response.getWriter().write(responseDTO.toJson());
    }

    private void handleCombat(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
        String combatDetailsJson = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        ResponseDTO responseDTO = dungeonService.engageCombat(id, combatDetailsJson);
        response.getWriter().write(responseDTO.toJson());
    }
}