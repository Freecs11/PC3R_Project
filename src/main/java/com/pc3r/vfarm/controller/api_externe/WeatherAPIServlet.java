package com.pc3r.vfarm.controller.api_externe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "weatherAPIServlet", urlPatterns = {"/weather-api"})
public class WeatherAPIServlet extends HttpServlet {

    private static final String API_KEY = "c3053ddd2658400182e170051242404";
    private static final String WEATHER_URL = "http://api.weatherapi.com/v1/current.json";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        if (city == null || city.trim().isEmpty()) {
            response.getWriter().println("City parameter is missing");
            return;
        }

        URL url = new URL(WEATHER_URL + "?key=" + API_KEY + "&q=" + city);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            response.getWriter().println("Failed : HTTP error code : " + conn.getResponseCode());
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        StringBuilder weatherData = new StringBuilder();
        while ((output = br.readLine()) != null) {
            weatherData.append(output);
        }

        conn.disconnect();

        response.setContentType("application/json");
        response.getWriter().println(weatherData.toString());
    }
}
