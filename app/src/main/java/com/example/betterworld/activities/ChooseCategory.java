package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityChooseCategoryBinding;

import static com.example.betterworld.utils.Actions.goToCharityFormActivity;

public class ChooseCategory extends AppCompatActivity {
    private ActivityChooseCategoryBinding activityChooseCategoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChooseCategoryBinding = DataBindingUtil.setContentView(this,R.layout.activity_choose_category);
        _initComponents();
    }

    private void _initComponents(){
        activityChooseCategoryBinding.btnNext.setOnClickListener(view -> goToCharityFormActivity(this));
    }
}