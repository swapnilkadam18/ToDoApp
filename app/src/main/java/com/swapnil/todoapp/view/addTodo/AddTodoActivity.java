package com.swapnil.todoapp.view.addTodo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;

import com.swapnil.todoapp.R;
import com.swapnil.todoapp.viewmodel.AddTodoViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddTodoActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private EditText title;
    private EditText desc;
    private EditText time;
    private EditText date;
    private SwitchCompat switchCompat;
    private Button addToDb;
    private AddTodoViewModel addTodoViewModel;
    private ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo_item);
        title = findViewById(R.id.etTitle);
        desc = findViewById(R.id.etDesc);
        time = findViewById(R.id.etTime);
        date = findViewById(R.id.etDate);
        switchCompat = findViewById(R.id.swDailyWeekly);
        addToDb = findViewById(R.id.btnSave);
        loader = findViewById(R.id.progressCircularAddToDo);

        addTodoViewModel = new ViewModelProvider(this).get(AddTodoViewModel.class);

        observeData();

        addToDb.setOnClickListener(this);
        switchCompat.setOnCheckedChangeListener(this);
    }

    private void observeData() {
        addTodoViewModel.showLoader
                .observe(this,isLoading -> loader.setVisibility(isLoading));

        addTodoViewModel.observeError.observe(this, errorMessage ->
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show());

        addTodoViewModel.onAddSuccess.observe(this,onSuccess -> finish());
    }

    @Override
    public void onClick(View view) {
        String inputTitle = title.getText().toString();
        String inputDesc = desc.getText().toString();
        String inputTime = time.getText().toString();
        String inputDate = date.getText().toString();
        boolean switchVal = switchCompat.isActivated();

        addTodoViewModel.addToDb(inputTitle,inputDesc,inputTime,inputDate,switchVal);


    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            switchCompat.setText("weekly");
        }else{
            switchCompat.setText("daily");
        }
    }
}