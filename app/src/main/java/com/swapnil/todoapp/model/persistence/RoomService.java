package com.swapnil.todoapp.model.persistence;

import com.swapnil.todoapp.model.persistence.pojo.TodoEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RoomService {

    @Inject
    public TodoDao roomServiceApi;

    @Inject
    public RoomService(){

    }

    public Single<List<TodoEntity>> getAllTodoItems(){
        return roomServiceApi.loadAllProducts();
    }

    public Completable addToDb(TodoEntity todoEntity){
        return roomServiceApi.insert(todoEntity);
    }

    public Completable deleteFromDb(TodoEntity todoEntity){
        return roomServiceApi.deleteItem(todoEntity);
    }
}
