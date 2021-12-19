package com.example.retrofitlogin.Model;

public class LoginResponse {

    private int id;
    private String token;

    public LoginResponse(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
