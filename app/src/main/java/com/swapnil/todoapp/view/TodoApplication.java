package com.swapnil.todoapp.view;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class TodoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
