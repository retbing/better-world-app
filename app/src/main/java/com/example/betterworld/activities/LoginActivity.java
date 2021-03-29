package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.betterworld.utils.Actions.goToRegisterActivity;
import static com.example.betterworld.utils.Actions.gotoMainActivity;
import static com.example.betterworld.utils.Constants.RC_SIGN_IN;


import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityLoginBinding;
import com.example.betterworld.models.User;
import com.example.betterworld.viewmodels.LoginViewModel;
import com.example.betterworld.viewmodels.RegisterViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    @Inject
    GoogleSignInClient googleSignInClient;
    TextView tv_create_new_account;
    ActivityLoginBinding activityLoginBinding;
    @Inject
    LoginViewModel loginViewModel;

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

        loginViewModel.signInWithEmailAndPassword(email,password);
        loginViewModel.authenticatedUserLiveData.observe(this, dataOrException ->{

            if (dataOrException.data != null) {
                Toast.makeText(LoginActivity.this, "Logged in successfully !", Toast.LENGTH_SHORT).show();

                gotoMainActivity(this, dataOrException.data);
            }

            if (dataOrException.exception != null) {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void _signingWithGoogle() {
            Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);
//                if (googleSignInAccount != null) {
//                    getGoogleAuthCredential(googleSignInAccount);
//                }
//            } catch (ApiException e) {
//                logErrorMessage(e.getMessage());
//            }
            Toast.makeText(this, "Logged in successfully !", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

}