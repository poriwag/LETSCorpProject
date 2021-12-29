package com.example.retrofitlogin.Model;

import com.google.gson.annotations.SerializedName;

public class UserList {

    private int page;

    @SerializedName("data")
    private UserData[] userData;

    public UserList(int page, UserData[] userData) {
        this.page = page;
        this.userData = userData;
    }

    public int getPage() {
        return page;
    }

    public UserData[] getUserData() {
        return userData;
    }
}
