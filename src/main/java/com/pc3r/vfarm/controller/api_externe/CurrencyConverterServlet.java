package com.pc3r.vfarm.controller.api_externe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name="Currency Currency" , value="/convertCurrency")
public class CurrencyConverterServlet extends HttpServlet {

    private static final String API_KEY = "fca_live_fs6Lx96pdAjNNUwwWxP7JHjSY5gRyKjWcsh3EC8m";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromCurrency = request.getParameter("from");
        String toCurrency = request.getParameter("to");
        String amount = request.getParameter("amount");

        String apiUrl = String.format("https://api.freecurrencyapi.com/v1/latest?apikey=%s&base_currency=%s&currencies=%s",
                API_KEY, fromCurrency, toCurrency);

        System.out.println("API URL: " + apiUrl);
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            response.getWriter().println("Échec de la requête FreeCurrencyAPI: code HTTP " + conn.getResponseCode());
            return;
        }

        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder jsonResponse = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonResponse.append(line);
        }
        reader.close();

        try {
            JSONObject json = new JSONObject(jsonResponse.toString());
            JSONObject rates = json.getJSONObject("data");
            double rate = rates.getDouble(toCurrency);
            double convertedAmount = rate * Double.parseDouble(amount);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.format("{\"convertedAmount\": %.2f}", convertedAmount));
        } catch (Exception e) {
            response.getWriter().println("Erreur lors du parsing de la réponse ou lors de la conversion des devises: " + e.getMessage());
        }
    }
}
