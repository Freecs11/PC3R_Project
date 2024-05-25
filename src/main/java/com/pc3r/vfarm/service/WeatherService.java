package com.pc3r.vfarm.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WeatherService {
    private static final String API_KEY = "c3053ddd2658400182e170051242404";
    private static final String WEATHER_URL = "http://api.weatherapi.com/v1/current.json";

    public List<Float> getWeather(float posX, float posY) throws IOException {
        URL url = new URL(WEATHER_URL + "?key=" + API_KEY + "&q=" + posX + "," + posY);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new IOException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        StringBuilder weatherData = new StringBuilder();
        while ((output = br.readLine()) != null) {
            weatherData.append(output);
        }
        output = weatherData.toString();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(output, JsonObject.class);
        JsonObject current = jsonObject.getAsJsonObject("current");
        Float temp = current.get("temp_c").getAsFloat();
        Float wind = current.get("wind_kph").getAsFloat();
        Float humidity = current.get("humidity").getAsFloat();
        Float pressure = current.get("pressure_mb").getAsFloat();
        conn.disconnect();
        return List.of(temp, wind, humidity, pressure);
    }
}
