package com.swapnil.todoapp.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.swapnil.todoapp.model.repository.TodoRepo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AddTodoViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    TodoRepo repo;

    @InjectMocks
    AddTodoViewModel viewModel = new AddTodoViewModel();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void addToDb() {
        String dummy = "";
        viewModel.addToDb(dummy,dummy,dummy,dummy,false);
        Mockito.verify(viewModel);

    }

    @Test
    public void onSuccess() {
    }

    @Test
    public void onError() {
    }
}