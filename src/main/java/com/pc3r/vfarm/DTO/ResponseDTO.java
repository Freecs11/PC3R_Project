package com.pc3r.vfarm.DTO;


import com.google.gson.Gson;

public class ResponseDTO {
    private String status;
    private String message;

    public ResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}