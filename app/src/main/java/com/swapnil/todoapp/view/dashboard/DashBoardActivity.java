package com.swapnil.todoapp.view.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swapnil.todoapp.R;
import com.swapnil.todoapp.model.persistence.pojo.TodoEntity;
import com.swapnil.todoapp.view.addTodo.AddTodoActivity;
import com.swapnil.todoapp.viewmodel.DashboardViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener, DashBoardAdapter.OnItemClickListener {

    private Button addTodo;
    private ProgressBar loader;
    private List<TodoEntity> todoList;
    private DashboardViewModel dashboardViewModel;
    private RecyclerView rvTodoList;
    private DashBoardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        addTodo = findViewById(R.id.btnAddTodo);
        loader = findViewById(R.id.progressCircularDash);
        rvTodoList = findViewById(R.id.rvTodoList);
        adapter = new DashBoardAdapter();
        dashboardViewModel = new ViewModelProvider(this)
                .get(DashboardViewModel.class);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvTodoList.getContext(),
                new LinearLayoutManager(this).getOrientation());
        rvTodoList.addItemDecoration(dividerItemDecoration);
        rvTodoList.setLayoutManager(new LinearLayoutManager(this));
        rvTodoList.setAdapter(adapter);
        addTodo.setOnClickListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dashboardViewModel.getAllTodo();
        observeLiveData();
    }

    private void observeLiveData() {
        dashboardViewModel.showLoader.observe(this,isLoading -> loader.setVisibility(isLoading));

        dashboardViewModel.liveTodoList.observe(this,
                list-> {
                    todoList = new ArrayList<>();
                    todoList = list;
                    adapter.updateList(todoList);
                });

        dashboardViewModel.observeError.observe(this, errorMessage ->
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onClick(View view) {
        Intent addNewItem = new Intent(this, AddTodoActivity.class);
        startActivity(addNewItem);
    }

    @Override
    public void onItemClick(TodoEntity selectedData) {
        dashboardViewModel.deleteTodo(selectedData);
    }
}