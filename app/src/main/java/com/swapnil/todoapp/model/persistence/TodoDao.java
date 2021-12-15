package com.swapnil.todoapp.model.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.swapnil.todoapp.model.persistence.pojo.TodoEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todoItems")
    Single<List<TodoEntity>> loadAllProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(TodoEntity todoItem);

    @Delete
    Completable deleteItem(TodoEntity entity);
}
