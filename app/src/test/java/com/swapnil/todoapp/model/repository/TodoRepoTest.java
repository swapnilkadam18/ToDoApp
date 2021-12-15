package com.swapnil.todoapp.model.repository;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.google.gson.internal.LinkedTreeMap;
import com.swapnil.todoapp.model.network.NetworkService;
import com.swapnil.todoapp.model.network.pojo.LoginRequest;
import com.swapnil.todoapp.model.persistence.RoomService;
import com.swapnil.todoapp.model.repository.interfaces.ViewModelCallback;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class TodoRepoTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    @InjectMocks
    TodoRepo repo = new TodoRepo();
    @Mock
    NetworkService networkService;
    @Mock
    RoomService roomService;
    @Mock
    LoginRequest loginRequest;
    @Mock
    ViewModelCallback callback;

    private Single<Object> testApiSingle;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public void setupRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable -> { runnable.run(); }, true);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }


    @After
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void authLoginRequest() {
        LinkedTreeMap<String,String> dummyRes = new LinkedTreeMap<String,String>();
        dummyRes.put("token","asdasdh");
        testApiSingle = Single.just(dummyRes);

        Mockito.when(networkService.getApiResponse(loginRequest)).thenReturn(testApiSingle);
        repo.authLoginRequest(callback,"asd","asd");
        Mockito.verify(callback).onSuccess();
    }

    @Test
    public void saveTodo() {
    }

    @Test
    public void getTodoList() {
    }

    @Test
    public void deleteTodoFromDB() {
    }
}