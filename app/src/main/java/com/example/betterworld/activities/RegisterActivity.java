package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.Toast;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityRegisterBinding;
import com.example.betterworld.models.User;
import com.example.betterworld.validatorRules.login.LoginFields;
import com.example.betterworld.validatorRules.register.RegisterFields;
import com.example.betterworld.viewmodels.RegisterViewModel;
import com.google.firebase.auth.FirebaseUser;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToLoginActivity;
import static com.example.betterworld.utils.Actions.gotoMainActivity;
import static com.example.betterworld.utils.Actions.gotoNotificationActivity;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

@AndroidEntryPoint
public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding activityRegisterBinding;

    @Inject
    RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(RegisterActivity.this, "Data changed on email and password", Toast.LENGTH_LONG).show();

        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        if (savedInstanceState == null) {
            registerViewModel.init();
        }

        activityRegisterBinding.setRegisterModel(registerViewModel);
        _initComponents();
    }

    private void _initComponents() {
        registerViewModel.getRegisterFields().observe(this, new Observer<RegisterFields>() {
            @Override
            public void onChanged(RegisterFields loginFields) {
                Toast.makeText(RegisterActivity.this, "Data changed on email and password", Toast.LENGTH_LONG).show();
            }
        });
        activityRegisterBinding.tvSignIn.setOnClickListener(view -> goToLoginActivity(this));
        activityRegisterBinding.btnRegister.setOnClickListener(view -> _createNewAccount());
    }

    private void _createNewAccount() {
        final String username = activityRegisterBinding.etUsername.getText().toString();
        final String email = activityRegisterBinding.etEmail.getText().toString();
        final String password = activityRegisterBinding.etPassword.getText().toString();

        registerViewModel.createNewAuthUser(email, password);

        registerViewModel.createdAuthUserLiveData.observe(this, dataOrException -> {
            if (dataOrException.data != null) {
                FirebaseUser user = dataOrException.data;
                User authenticatedUser = new User(user.getUid(), username, user.getEmail());
                createNewUser(authenticatedUser);
                gotoNotificationActivity(RegisterActivity.this);

            }

            if (dataOrException.exception != null) {
                logErrorMessage(dataOrException.exception.getMessage());
            }
        });
    }

    private void createNewUser(User authenticatedUser) {
//        displayProgressBar();
        registerViewModel.createUser(authenticatedUser);
        registerViewModel.createdUserLiveData.observe(this, dataOrException -> {
            if (dataOrException.data != null) {
                gotoMainActivity(this);
            }

            if (dataOrException.exception != null) {
                logErrorMessage(dataOrException.exception.getMessage());
            }
        });
    }

}