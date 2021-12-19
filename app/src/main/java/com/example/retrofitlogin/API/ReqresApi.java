package com.example.retrofitlogin.API;

import com.example.retrofitlogin.Model.LoginResponse;
import com.example.retrofitlogin.Model.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReqresApi {


    @POST("register")
    Call<LoginResponse> loginRequest(
            @Body UserLogin user
    );
}
