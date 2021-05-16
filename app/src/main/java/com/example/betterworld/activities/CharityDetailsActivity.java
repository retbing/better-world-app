package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.betterworld.databinding.ActivityCharityDetailsBinding;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class CharityDetailsActivity extends AppCompatActivity {


    private ActivityCharityDetailsBinding activityCharityDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}