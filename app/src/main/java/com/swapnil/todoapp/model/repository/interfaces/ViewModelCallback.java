package com.swapnil.todoapp.model.repository.interfaces;

import com.swapnil.todoapp.model.persistence.pojo.TodoEntity;

import java.util.List;

public interface ViewModelCallback {
    void onSuccess();
    void onError(String errorMsg);
    void onDbFetchSuccess(List<TodoEntity> entities);
}
