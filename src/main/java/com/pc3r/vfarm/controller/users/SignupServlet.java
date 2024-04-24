package com.pc3r.vfarm.controller.users;

import com.pc3r.vfarm.DTO.ResponseDTO;
import com.pc3r.vfarm.entities.User;
import com.pc3r.vfarm.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signupServlet", value = "/api/v1/user/signup")
public class SignupServlet extends HttpServlet {

    private UserService userService;
    public void init() {
        this.userService = new UserService();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // sign up a new user with the given username, password and email
        response.setContentType("application/json");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = userService.getUserByUsername(username);

        if (user != null) {
            ResponseDTO responseDTO = new ResponseDTO("error", "Username already exists");
            response.getWriter().write(responseDTO.toJson());
            return;
        }

        user = userService.getUserByEmail(email);

        if (user != null) {
            ResponseDTO responseDTO = new ResponseDTO("error", "Email already exists");
            response.getWriter().write(responseDTO.toJson());
            return;
        }

        user = userService.createUser(username, password, email, "user");

        // maybe catch exception here ( normally it should not happen)
        // if (user == null) ...

        ResponseDTO responseDTO = new ResponseDTO("success", "User created successfully");
        response.getWriter().write(responseDTO.toJson());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        // when we receive a get request,
        // we return a json object with the message "Method not allowed and the status code 405
        // i think it's best to leave the signup endpoint only for post requests
        ResponseDTO responseDTO = new ResponseDTO("error", "Method not allowed");
        response.getWriter().write(responseDTO.toJson());
        response.setStatus(405);

    }
}
