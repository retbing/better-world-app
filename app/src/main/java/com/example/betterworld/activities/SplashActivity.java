package com.example.betterworld.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betterworld.R;
import com.example.betterworld.models.User;
import com.example.betterworld.utils.Delayer;
import com.example.betterworld.viewmodels.SplashViewModel;


import javax.inject.Inject;

import static com.example.betterworld.utils.Actions.goToLoginActivity;
import static com.example.betterworld.utils.Actions.gotoMainActivity;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {
    @Inject
    SplashViewModel splashViewModel;

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Delayer.delay(1000, new Delayer.DelayCallback() {
            @Override
            public void callback() {
                checkIfUserIsAuthenticated();
            }
        });


    }

    private void checkIfUserIsAuthenticated() {
        boolean isUserAuthenticated = splashViewModel.checkIfUserIsAuthenticated();
        if (isUserAuthenticated) {
            String uid = splashViewModel.getUid();
            Toast.makeText(this, "User "+uid, Toast.LENGTH_LONG).show();
            goToLoginActivity(this);
        } else {
            Log.d(TAG, "checkIfUserIsAuthenticated: go login page");
            goToLoginActivity(this);
        }
    }

    private void getUserData(String uid) {
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
