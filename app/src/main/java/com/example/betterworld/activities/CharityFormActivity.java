package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityChooseCategoryBinding;

public class CharityFormActivity extends AppCompatActivity {
    ActivityChooseCategoryBinding activityChooseCategoryBinding;
    private int step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChooseCategoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_charity_form);
        step = 1;
    }


    private void _initComponents() {
        activityChooseCategoryBinding.btnNext.setOnClickListener(view -> _nextStep());
    }

    private void _nextStep() {
        switch (step){
            case 1:
                activityChooseCategoryBinding.btn
        }
    }

}