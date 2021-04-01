package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityCharityFormBinding;
import com.example.betterworld.databinding.ActivityChooseCategoryBinding;
import com.example.betterworld.models.Charity;
import com.example.betterworld.viewmodels.CharityViewModel;

import javax.inject.Inject;

import static com.example.betterworld.utils.Actions.goToRegisterActivity;

public class CharityFormActivity extends AppCompatActivity {
    private ActivityCharityFormBinding activityCharityFormBinding;

    @Inject
     CharityViewModel charityViewModel;
    private int step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCharityFormBinding = DataBindingUtil.setContentView(this, R.layout.activity_charity_form);
        _initComponents();
        step = 1;
    }


    private void _initComponents() {

        activityCharityFormBinding.btnNext.setOnClickListener(view -> _createCharity());

    }

    private void _createCharity() {
        charityViewModel.createCharity();



    }

    private void _nextStep() {
        switch (step){
            case 1:

        }
    }

}