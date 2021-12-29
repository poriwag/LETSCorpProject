package com.example.retrofitlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitlogin.API.ReqresApi;
import com.example.retrofitlogin.Model.LoginResponse;
import com.example.retrofitlogin.Model.UserList;
import com.example.retrofitlogin.Model.UserLogin;
import com.example.retrofitlogin.Model.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ReqresApi reqresApi;

    private EditText editEmail;
    private EditText editPassword;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        loginButton = (Button) findViewById(R.id.loginButton);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reqresApi = retrofit.create(ReqresApi.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                //validate form
                if(validateLogin(username, password)) {
                    login(username, password);
                }
            }
        });

        reqresData();
    }

    private void  configureUIElements() {
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                //validate form
                if(validateLogin(username, password)) {
                    login(username, password);
                }
            }
        });
    }

    private Boolean validateLogin(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void login(final String email, final String password) {
        UserLogin userLogin = new UserLogin(email, password);
        Call<LoginResponse> call = reqresApi.loginRequest(userLogin);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.isSuccessful()) {
                    int executed = Log.d("Status Cod 400", "Executed");
                    Toast.makeText(MainActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginResponse postResponse = response.body();
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("token", postResponse.getToken());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("onFailure", "called");
            }
        });
    }

    private void reqresData() {
        Call<UserList> call = reqresApi.getData();

        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if(!response.isSuccessful()) {
                    Log.d("status code", "Status code" + response.code());
                }
                UserList userList = response.body();

                UserData[] userData = userList.getUserData();

                Log.d("response", "data:" + userList.getPage());
                Log.d("response", "firstName:" + userList.getUserData().length);


            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d("onFailure", "JSON Request Failed for GET Method");
            }
        });
    }

    private void getUsers() {
        Call<List<UserData>> call = reqresApi.getUsers();

        call.enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
                if(!response.isSuccessful()) {
                    Log.d("status code", "Status code" + response.code());
                }
                List<UserData> userData = response.body();

                for (UserData user: userData) {
                    Log.d("printed JSON", "" + user.getFirstName());
                }
            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                Log.d("onFailure", "JSON Request Failed for GET Method");
            }
        });

    }

}