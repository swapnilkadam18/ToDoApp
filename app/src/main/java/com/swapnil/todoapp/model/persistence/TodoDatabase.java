package com.swapnil.todoapp.model.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.swapnil.todoapp.model.persistence.pojo.TodoEntity;

@Database(entities = TodoEntity.class, version = 1)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
}
