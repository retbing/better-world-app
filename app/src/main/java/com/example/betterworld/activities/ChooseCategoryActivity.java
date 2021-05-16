package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityChooseCategoryBinding;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToCharityFormActivity;

@AndroidEntryPoint
public class ChooseCategoryActivity extends AppCompatActivity {
    private ActivityChooseCategoryBinding activityChooseCategoryBinding;
    AppCompatButton btnAnimal, btnEducation, btnEnvironment, btnHealth, btnCultureArts;
    Drawable bg_active, bg_default;
    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChooseCategoryBinding = DataBindingUtil.setContentView(this,R.layout.activity_choose_category);
        _initComponents();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void _initComponents(){
        bg_active = getResources().getDrawable(R.drawable.button_bg_charity_category_active);
        bg_default = getResources().getDrawable(R.drawable.button_bg_charity_category);

        btnAnimal = activityChooseCategoryBinding.btnAnimal;
        btnCultureArts = activityChooseCategoryBinding.btnCultureArts;
        btnEducation = activityChooseCategoryBinding.btnEducation;
        btnEnvironment = activityChooseCategoryBinding.btnEnvironment;
        btnHealth = activityChooseCategoryBinding.btnHealth;

        this._setDefaultBackground();

        this.btnAnimal.setOnClickListener(view -> checkCategory("Animal"));
        this.btnCultureArts.setOnClickListener(view -> checkCategory("CultureArts"));
        this.btnEducation.setOnClickListener(view -> checkCategory("Education"));
        this.btnEnvironment.setOnClickListener(view -> checkCategory("Environment"));
        this.btnHealth.setOnClickListener(view -> checkCategory("Health"));

        activityChooseCategoryBinding.btnNext.setOnClickListener(view -> goToCharityFormActivity(this, categoryName));
    }


    private void checkCategory(String category){
        switch (category){
            case "Animal":
                if(this.btnAnimal.getBackground() == bg_default){
                    this._setDefaultBackground();
                    this.categoryName = "Animal";
                    this.btnAnimal.setBackground(bg_active);
                }else{
                    this.btnAnimal.setBackground(bg_default);
                }
                break;
            case "Health":
                if(this.btnHealth.getBackground() == bg_default){
                    this._setDefaultBackground();
                    this.categoryName = "Health";
                    this.btnHealth.setBackground(bg_active);
                }else{
                    this.btnHealth.setBackground(bg_default);
                }
                break;
            case "Education":
                if(this.btnEducation.getBackground() == bg_default){
                    this._setDefaultBackground();
                    this.categoryName = "Education";
                    this.btnEducation.setBackground(bg_active);
                }else{
                    this.btnEducation.setBackground(bg_default);
                }
                break;
            case "Environment":
                if(this.btnEnvironment.getBackground() == bg_default){
                    this._setDefaultBackground();
                    this.categoryName = "Environment";
                    this.btnEnvironment.setBackground(bg_active);
                }else{
                    this.btnEnvironment.setBackground(bg_default);
                }
                break;
            case "CultureArts":
                if(this.btnCultureArts.getBackground() == bg_default){
                    this._setDefaultBackground();
                    this.categoryName = "CultureArts";
                    this.btnCultureArts.setBackground(bg_active);
                }else{
                    this.btnCultureArts.setBackground(bg_default);
                }
                break;
        }
    }

    private void _setDefaultBackground(){
        this.categoryName = "";
        this.btnAnimal.setBackground(bg_default);
        this.btnHealth.setBackground(bg_default);
        this.btnEnvironment.setBackground(bg_default);
        this.btnEducation.setBackground(bg_default);
        this.btnCultureArts.setBackground(bg_default);

    }

    private void isEmptyCategoryName(){

    }
}