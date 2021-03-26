package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import static com.example.betterworld.utils.Actions.goToRegisterActivity;


import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    TextView tv_create_new_account;
    ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        _initComponents();
    }

    private void _initComponents() {
        activityLoginBinding.tvCreateNewAccount.setOnClickListener(view -> goToRegisterActivity(this));
        activityLoginBinding.btnLogin.setOnClickListener(view -> _loginWithEmailAndPassword());
        activityLoginBinding.btnGoogleSignin.setOnClickListener(view -> _signingWithGoogle());
    }

    private void _loginWithEmailAndPassword() {
        final String email = activityLoginBinding.etEmail.getText().toString();
        final String password = activityLoginBinding.etPassword.getText().toString();
        // TODO: bind login function here
        // responsible: biniyam
    }

    private void _signingWithGoogle() {
        // TODO: bind sign in with google function here
        // responsible: biniyam
    }
}