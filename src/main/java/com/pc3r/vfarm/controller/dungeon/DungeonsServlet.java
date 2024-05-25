package com.pc3r.vfarm.controller.dungeon;

import com.pc3r.vfarm.DTO.ResponseDTO;
import com.pc3r.vfarm.entities.Dungeon;
import com.pc3r.vfarm.service.DungeonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "dungeonsServlet", urlPatterns = {"/api/v1/dungeons"})
public class DungeonsServlet extends HttpServlet {
    private DungeonService dungeonService;

    @Override
    public void init() {
        this.dungeonService = new DungeonService();
    }

    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        float posX = Float.parseFloat(request.getParameter("posX"));
        float posY = Float.parseFloat(request.getParameter("posY"));
        ResponseDTO responseDTO = dungeonService.getAllDungeons(posX, posY);
        response.getWriter().write(responseDTO.toJson());
    }
}
