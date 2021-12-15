package com.swapnil.todoapp.model.network;

import com.swapnil.todoapp.model.network.pojo.LoginRequest;

import javax.inject.Inject;

import io.reactivex.Single;

public class NetworkService {

    @Inject
    public NetworkServiceApi api;

    @Inject
    public NetworkService() {
    }

    public Single<Object> getApiResponse(LoginRequest request){
        return api.getApiResponse(request);
    }
}
