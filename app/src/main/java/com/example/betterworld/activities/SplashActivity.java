package com.example.betterworld.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betterworld.R;
import com.example.betterworld.models.User;
import com.example.betterworld.viewmodels.SplashViewModel;


import javax.inject.Inject;

import static com.example.betterworld.utils.Actions.gotoAuthActivity;
import static com.example.betterworld.utils.Actions.gotoMainActivity;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {
    @Inject
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkIfUserIsAuthenticated();

    }

    private void checkIfUserIsAuthenticated() {
        boolean isUserAuthenticated = splashViewModel.checkIfUserIsAuthenticated();
        if (isUserAuthenticated) {
            String uid = splashViewModel.getUid();
            getUserData(uid);
        } else {
            gotoAuthActivity(this);
        }
    }

    private void getUserData(String uid) {
        splashViewModel.setUid(uid);
        splashViewModel.userLiveData.observe(this, dataOrException -> {
            if (dataOrException.data != null) {
                User user = dataOrException.data;
                gotoMainActivity(this, user);
            }

            if (dataOrException.exception != null) {
                logErrorMessage(dataOrException.exception.getMessage());
            }
        });
    }

}
