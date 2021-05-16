package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityHomeBinding;
import com.example.betterworld.databinding.ActivityLoginBinding;
import com.example.betterworld.viewmodels.HomeViewModel;
import com.example.betterworld.viewmodels.LoginViewModel;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding activityHomeBinding;
    @Inject
    HomeViewModel homeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

    }
}