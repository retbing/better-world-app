package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.betterworld.utils.Actions.goToForgotPasswordActivity;
import static com.example.betterworld.utils.Actions.goToRegisterActivity;
import static com.example.betterworld.utils.Actions.gotoMainActivity;


import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityLoginBinding;
import com.example.betterworld.validatorRules.login.LoginFields;
import com.example.betterworld.viewmodels.LoginViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    @Inject
    GoogleSignInClient googleSignInClient;
    TextView tv_create_new_account;
    ActivityLoginBinding activityLoginBinding;

    final String TAG = "LoginActivity";

    @Inject
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        if (savedInstanceState == null) {
            loginViewModel.init();
        }

        activityLoginBinding.setLoginModel(loginViewModel);
        _initComponents();
        setupButtonClick();
    }

    private void setupButtonClick() {
        loginViewModel.getLoginFields().observe(this, new Observer<LoginFields>() {
            @Override
            public void onChanged(LoginFields loginFields) {
                _loginWithEmailAndPassword();
            }
        });
    }

    private void _initComponents() {
        activityLoginBinding.tvForgotPassword.setOnClickListener(view -> goToForgotPasswordActivity(this));
        activityLoginBinding.tvCreateNewAccount.setOnClickListener(view ->
                goToRegisterActivity(this));
        activityLoginBinding.btnLogin.setOnClickListener(view ->
                _loginWithEmailAndPassword());
        activityLoginBinding.btnGoogleSignin.setOnClickListener(view ->
                _signingWithGoogle());


    }

    private void _loginWithEmailAndPassword() {
        final String email = activityLoginBinding.etEmail.getText().toString();
        final String password = activityLoginBinding.etPassword.getText().toString();
        loginViewModel.signInWithEmailAndPassword(email, password);
        loginViewModel.authenticatedUserLiveData.observe(this, dataOrException -> {
            if (dataOrException.data != null) {
                Toast.makeText(LoginActivity.this, "Logged in successfully as " + dataOrException.data.getUsername(), Toast.LENGTH_SHORT).show();
                gotoMainActivity(LoginActivity.this);
            }

            if (dataOrException.exception != null) {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void _signingWithGoogle() {
    }



}