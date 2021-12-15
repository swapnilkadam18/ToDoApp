package com.swapnil.todoapp.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.swapnil.todoapp.R;
import com.swapnil.todoapp.view.dashboard.DashBoardActivity;
import com.swapnil.todoapp.viewmodel.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText etUserName;
    private EditText etPassword;
    private ProgressBar loader;

    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        loader = findViewById(R.id.progressCircularLogin);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        observeData();
        btnLogin.setOnClickListener(this);
    }

    private void observeData() {

        loginViewModel.showLoader.observe(this,isLoading -> loader.setVisibility(isLoading));

        loginViewModel.observeError.observe(this, errorMessage -> Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show());

        loginViewModel.onLoginSuccess.observe(this, onSuccess-> openDashboard());
    }

    private void openDashboard() {
        Intent dashboardIntent = new Intent(this, DashBoardActivity.class);
        startActivity(dashboardIntent);
    }

    @Override
    public void onClick(View view) {

        //Since login URL stopped working I have bypassed the login auth check, to test just
        //uncomment the below code and comment the code at line 67

//        loginViewModel.authenticateUser(etUserName.getText().toString(),
//                etPassword.getText().toString());

        openDashboard();
    }
}