package com.swapnil.todoapp.model.network.pojo;

import com.google.gson.annotations.SerializedName;

public class LoginSuccessResponse {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
