package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityPaymentMethodBinding;

import static com.example.betterworld.utils.Actions.goToCharityStartActivity;
import static com.example.betterworld.utils.Actions.gotoProfileActivity;

public class PaymentMethodActivity extends AppCompatActivity {

    ActivityPaymentMethodBinding activityPaymentMethodBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        activityPaymentMethodBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_method);
        _initComponents();
    }

    void _initComponents(){
        activityPaymentMethodBinding.btnBlur.setOnClickListener(view -> {
            goToCharityStartActivity(this);
        });
        activityPaymentMethodBinding.btnProfile.setOnClickListener(view -> {
            gotoProfileActivity(this);
        });

        activityPaymentMethodBinding.btnReturnPage.setOnClickListener(view -> gotoProfileActivity(this));
    }
}