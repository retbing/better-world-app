package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityForgotPasswordBinding;
import com.example.betterworld.viewmodels.ForgotPasswordViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding activityForgotPasswordBinding;
    @Inject
    ForgotPasswordViewModel forgotPasswordViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityForgotPasswordBinding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);
        _initComponents();
    }

    private void _initComponents(){
        activityForgotPasswordBinding.btnSubmit.setOnClickListener(view -> _sendEmailMessageToForgotPassword());

    }

    private void _sendEmailMessageToForgotPassword(){
        String email = activityForgotPasswordBinding.etEmailForFpassword.getText().toString();
        if(!email.equals("")){
            forgotPasswordViewModel.resetPassword(email).observe(this, password -> {
                if(password.data != null){
                    Snackbar.make(activityForgotPasswordBinding.clResetPassword,"Password reset link sent to your email.", Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(activityForgotPasswordBinding.clResetPassword, password.exception.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }
}