package com.swapnil.todoapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swapnil.todoapp.model.persistence.pojo.TodoEntity;
import com.swapnil.todoapp.model.repository.TodoRepo;
import com.swapnil.todoapp.model.repository.interfaces.ViewModelCallback;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
@HiltViewModel
public class DashboardViewModel extends ViewModel implements ViewModelCallback {

    public MutableLiveData<Integer> showLoader = new MutableLiveData<>();
    public MutableLiveData<String> observeError = new MutableLiveData<>();
    public MutableLiveData<List<TodoEntity>> liveTodoList = new MutableLiveData<>();

    private final int visible = 0;
    private final int gone = 8;

    @Inject
    public TodoRepo todoRepo;

    @Inject
    public DashboardViewModel(){

    }

    public void getAllTodo(){
        showLoader.setValue(visible);
        todoRepo.getTodoList(this);
    }

    public void deleteTodo(TodoEntity selectedEntity){
        showLoader.setValue(visible);
        todoRepo.deleteTodoFromDB(this,selectedEntity);
    }

    @Override
    public void onSuccess() {
        getAllTodo();
    }

    @Override
    public void onError(String errorMsg) {
        showLoader.setValue(gone);
        observeError.setValue(errorMsg);
    }

    @Override
    public void onDbFetchSuccess(List<TodoEntity> entities) {
        showLoader.setValue(gone);
        liveTodoList.setValue(entities);
    }
}
