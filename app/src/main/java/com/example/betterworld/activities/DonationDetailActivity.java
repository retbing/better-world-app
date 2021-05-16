package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityDonationDetailBinding;

public class DonationDetailActivity extends AppCompatActivity {
    ActivityDonationDetailBinding activityDonationDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDonationDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_donation_detail);

    }
}