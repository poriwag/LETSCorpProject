package com.example.retrofitlogin.Model;

public class UserData {

    private int id;
    private String email;
    private String firstName;

    public UserData(int id, String email, String firstName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }
}
