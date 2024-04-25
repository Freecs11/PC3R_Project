package com.pc3r.vfarm.controller;

import java.io.*;

import com.pc3r.vfarm.database.DatabaseConnection;
import com.pc3r.vfarm.entities.User;
import com.pc3r.vfarm.service.UserService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        DatabaseConnection.getSessionFactory();
        message = "Hello World!";

        try {
            UserService userService = new UserService();
            User user = new User();

        } catch (Exception e) {
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}