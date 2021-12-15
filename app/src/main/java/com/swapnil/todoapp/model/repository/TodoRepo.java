package com.swapnil.todoapp.model.repository;

import com.google.gson.internal.LinkedTreeMap;
import com.swapnil.todoapp.model.network.NetworkService;
import com.swapnil.todoapp.model.network.pojo.LoginRequest;
import com.swapnil.todoapp.model.persistence.RoomService;
import com.swapnil.todoapp.model.persistence.pojo.TodoEntity;
import com.swapnil.todoapp.model.repository.interfaces.ViewModelCallback;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TodoRepo {

    @Inject
    public NetworkService networkService;

    @Inject
    public RoomService roomService;

    @Inject
    public TodoRepo() {

    }

    public void authLoginRequest(ViewModelCallback callback, String userName, String password) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(networkService.getApiResponse(new LoginRequest(userName, password))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Object>() {
                    @Override
                    public void onSuccess(@NonNull Object expectedRes) {
                        if (((LinkedTreeMap) expectedRes).get("token") != null) {
                            callback.onSuccess();
                        } else {
                            callback.onError("Authentication failed");
                        }
                        disposable.dispose();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        disposable.dispose();
                        callback.onError("Authentication failed because " + e.getLocalizedMessage());
                    }
                })
        );
    }

    public void saveTodo(ViewModelCallback callback, String inputTitle, String inputDesc, String inputTime,
                         String inputDate, boolean switchVal) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(roomService.addToDb(new TodoEntity(inputTitle, inputDesc, inputTime, inputDate, switchVal))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                            callback.onSuccess();
                            disposable.dispose();
                        },
                        throwable -> {
                            callback.onError("Unable to save");
                            disposable.dispose();
                        }));

    }

    public void getTodoList(ViewModelCallback callback) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(roomService.getAllTodoItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(todoList -> {
                            callback.onDbFetchSuccess(todoList);
                            disposable.dispose();
                        },
                        throwable -> {
                            callback.onError("Unable to get list");
                            disposable.dispose();
                        }));
    }

    public void deleteTodoFromDB(ViewModelCallback callback,
                                 TodoEntity todoEntity) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(roomService.deleteFromDb(todoEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                            callback.onSuccess();
                            disposable.dispose();
                        },
                        throwable -> {
                            callback.onError("Unable to delete");
                            disposable.dispose();
                        }));
    }
}
