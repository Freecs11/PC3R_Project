package com.pc3r.vfarm.controller;


import com.pc3r.vfarm.dao.UserDAO;
import com.pc3r.vfarm.entities.User;
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
        String id = request.getParameter("id");

        User user = userDAO.findById(Integer.parseInt(id));
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());

        // Check if the user exists in the database.
        if (user != null) {
            // Check if the username and password are correct.
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                //get the old session and invalidate
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                //generate a new session
                HttpSession newSession = request.getSession(true);

                //setting session to expiry in 5 mins
                newSession.setMaxInactiveInterval(5 * 60);

            } else {
                // If the username and password are incorrect, display an error message.
                out.println("{\"status\": \"error\", \"message\": \"Invalid username or password\"}");
            }
        } else {
            // If the user does not exist in the database, display an error message.
            out.println("{\"status\": \"error\", \"message\": \"User does not exist\"}");
        }
        // Close the print writer object.
        out.close();
    }
}
