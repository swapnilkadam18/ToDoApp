package com.swapnil.todoapp.model.persistence.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todoItems")
public class TodoEntity {

    @PrimaryKey(autoGenerate = true)
    private int todoId;

    private String title;
    private String description;
    private String time;
    private String date;
    //true: weekly false daily
    private boolean type;

    public TodoEntity(String title, String description, String time,
                      String date, boolean type) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.date = date;
        this.type = type;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
