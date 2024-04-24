package com.pc3r.vfarm.DTO;

public class ResponseDTO {
    private String status;
    private String message;

    public ResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        return "{\"status\": \"" + status + "\", \"message\": \"" + message + "\"}";
    }
}
