package com.swapnil.todoapp.model.network;

import com.swapnil.todoapp.model.network.pojo.LoginRequest;
import com.swapnil.todoapp.model.utils.Constants;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkServiceApi {
    @POST(Constants.LOGIN_URL)
    Single<Object> getApiResponse(@Body LoginRequest request);
}
