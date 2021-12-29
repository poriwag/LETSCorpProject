package com.example.retrofitlogin.API;

import com.example.retrofitlogin.Model.LoginResponse;
import com.example.retrofitlogin.Model.UserData;
import com.example.retrofitlogin.Model.UserList;
import com.example.retrofitlogin.Model.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ReqresApi {

    @GET("users?page=2")
    Call<UserList> getData();

    @GET("users")
    Call<List<UserData>> getUsers();

    @POST("register")
    Call<LoginResponse> loginRequest(
            @Body UserLogin user
    );
}
