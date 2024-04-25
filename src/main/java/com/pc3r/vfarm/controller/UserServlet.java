package com.pc3r.vfarm.controller;

import com.pc3r.vfarm.entities.User;
import com.pc3r.vfarm.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

@WebServlet(name = "userServlet", value = "/users")
public class UserServlet extends HttpServlet {
    private UserService userService;
    public void init() {
        this.userService = new UserService();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String userID = request.getParameter("id");
        String userName = request.getParameter("name");
        String userPassword = request.getParameter("password");
        String userEmail = request.getParameter("email");
        PrintWriter out = response.getWriter();
        User user = new User();
        user.setId(Integer.parseInt(userID));
        user.setUsername(userName);
        user.setPassword(userPassword);
        user.setEmail(userEmail);
        user.setCoinId("0");
        user.setRole("user");
        user.setPosition("0");
        user.setCreatedAt(Instant.now());

        userService.save(user);
        out.println("{\"status\": \"success\"}");
    }
}
