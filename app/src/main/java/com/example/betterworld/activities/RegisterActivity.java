package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityRegisterBinding;
import com.example.betterworld.models.User;
import com.example.betterworld.viewmodels.RegisterViewModel;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToLoginActivity;

@AndroidEntryPoint
public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding activityRegisterBinding;
    @Inject RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        _initComponents();
    }

    private void _initComponents() {
        activityRegisterBinding.tvSignIn.setOnClickListener(view -> goToLoginActivity(this));
        activityRegisterBinding.btnRegister.setOnClickListener(view -> _createNewAccount());
    }

    private void _createNewAccount() {
        final String username = activityRegisterBinding.etUsername.getText().toString();
        final String email = activityRegisterBinding.etEmail.getText().toString();
        final String password = activityRegisterBinding.etPassword.getText().toString();

        // TODO: bind register function here
        // responsible: biniyam
        registerViewModel.createUser(email, password,username);
    }

}