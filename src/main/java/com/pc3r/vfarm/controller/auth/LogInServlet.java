package com.pc3r.vfarm.controller.auth;


import com.pc3r.vfarm.DTO.ResponseDTO;
import com.pc3r.vfarm.dao.UserDAO;
import com.pc3r.vfarm.entities.User;
import com.pc3r.vfarm.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "logInServlet", value = "/login")
public class LogInServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();


    public void init() {
    }

    public void destroy() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set the content type of response to "text/html"
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        // Get User entered details from the request using request parameter.
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.getUserByUsername(username);
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());

        // Check if the user exists in the database.
        if (user != null) {
            // Check if the username and password are correct.
            if (user.getUsername().equals(username) && userDAO.checkPassword(password, user.getPassword())) {
                //get the old session and invalidate
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                //generate a new session
                HttpSession newSession = request.getSession(true);

                //setting session to expiry in 1 day (24 hours)
                newSession.setMaxInactiveInterval(60 * 60 * 24);

            } else {
                ResponseDTO responseDTO = new ResponseDTO("error", "Invalid username or password");
                out.print(responseDTO);
            }
        }
        // Close the print writer object.
        out.close();
    }
}