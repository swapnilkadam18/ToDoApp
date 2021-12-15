package com.swapnil.todoapp.model.network.pojo;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("email")
    final String usrName;
    @SerializedName("password")
    final String pwd;

    public LoginRequest(String userName, String password) {
        usrName = userName;
        pwd = password;
    }
}
