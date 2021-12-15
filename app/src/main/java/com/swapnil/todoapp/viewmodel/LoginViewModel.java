package com.swapnil.todoapp.viewmodel;

import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swapnil.todoapp.model.persistence.pojo.TodoEntity;
import com.swapnil.todoapp.model.repository.TodoRepo;
import com.swapnil.todoapp.model.repository.interfaces.ViewModelCallback;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel implements ViewModelCallback {

    public MutableLiveData<Integer> showLoader = new MutableLiveData<>();
    public MutableLiveData<String> observeError = new MutableLiveData<>();
    public MutableLiveData<Boolean> onLoginSuccess = new MutableLiveData<>();

    private final int visible = 0;
    private final int gone = 8;

    @Inject
    public TodoRepo repo;

    @Inject
    public LoginViewModel() {
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void authenticateUser(String userName, String password) {
        if(userName.isEmpty() || password.isEmpty()){
            observeError.setValue("UserName or Password cannot be empty");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(userName).matches()){
            observeError.setValue("Invalid Username");
        }else{
            showLoader.setValue(visible);
            repo.authLoginRequest(this,userName,password);
        }
    }

    @Override
    public void onSuccess() {
        showLoader.setValue(gone);
        onLoginSuccess.setValue(true);
    }

    @Override
    public void onError(String errorMsg) {
        showLoader.setValue(gone);
        observeError.setValue(errorMsg);
    }

    @Override
    public void onDbFetchSuccess(List<TodoEntity> entities) {

    }
}
