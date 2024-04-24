package com.pc3r.vfarm.controller;

import com.pc3r.vfarm.DTO.ResponseDTO;
import com.pc3r.vfarm.DTO.UserDto;
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
        PrintWriter out = response.getWriter();

        UserDto userDTO = extractUserDTOFromRequest(request);
        User user = convertDTOToUser(userDTO);
        userService.save(user);

        ResponseDTO responseDTO = new ResponseDTO("success", "User created successfully");
        out.println(responseDTO.toJson());
    }

    private UserDto extractUserDTOFromRequest(HttpServletRequest request) {
        UserDto userDTO = new UserDto();
        userDTO.setUsername(request.getParameter("username"));
        userDTO.setPassword(request.getParameter("password"));
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setCoinId(Integer.valueOf(request.getParameter("coinId")));
        userDTO.setRole(request.getParameter("role"));
        userDTO.setPosition(request.getParameter("position"));
        userDTO.setCreatedAt(Instant.now());
        return userDTO;
    }

    private User convertDTOToUser(UserDto userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setCoinId(userDTO.getCoinId());
        user.setRole(userDTO.getRole());
        user.setPosition(userDTO.getPosition());
        user.setCreatedAt(Instant.now());
        return user;
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String action = request.getParameter("action");

        if (action.equals("get_user_by_username")) {
            String username = request.getParameter("username");
            User user = userService.getUserByUsername(username);
            ResponseDTO responseDTO = new ResponseDTO("success", user.toString());
            PrintWriter out = response.getWriter();
            out.println(responseDTO.toJson());
        } else if (action.equals("get_user_by_email")) {
            String email = request.getParameter("email");
            User user = userService.getUserByEmail(email);
            ResponseDTO responseDTO = new ResponseDTO("success", user.toString());
            PrintWriter out = response.getWriter();
            out.println(responseDTO.toJson());
        }

    }



}
