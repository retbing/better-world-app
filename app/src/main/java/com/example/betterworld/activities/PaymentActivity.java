package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityLoginBinding;
import com.example.betterworld.databinding.ActivityPaymentBinding;
import com.example.betterworld.viewmodels.PaymentViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PaymentActivity extends AppCompatActivity {

    @Inject
     PaymentViewModel paymentViewModel;
    ActivityPaymentBinding activityPaymentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPaymentBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment);
        _initComponents();
    }

    private void _initComponents() {
        activityPaymentBinding.donateToCharity.setOnClickListener(view-> {
            Toast.makeText(PaymentActivity.this, "Creeating Payment ", Toast.LENGTH_SHORT).show();
            createPayment();
        });
        activityPaymentBinding.radioGroupTogglePaymentMtd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,  int i) {
                Toast.makeText(PaymentActivity.this, "I am changed", Toast.LENGTH_SHORT).show();
                togglePaymentView(i);
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    AppCompatRadioButton view = (AppCompatRadioButton ) radioGroup.getChildAt(j);
                    view.setChecked(view.getId() == i);
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String msg = "";
        // We'll check which radiobutton is clicked
        switch (view.getId()) {
            case R.id.rl_BankView:
                if (checked)
                    msg = "You Clicked Bhopal";
                break;
            case R.id.rl_CreditCardView:
                if (checked)
                    msg = "You Clicked Bangalore ";
                break;
            case R.id.rl_PaypalView:
                if (checked)
                    msg = "You Clicked Delhi ";
                break;
        }
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void togglePaymentView(int i) {
        switch (i){
            case 0: {
                activityPaymentBinding.rlCreditCardView.setVisibility(View.VISIBLE);
                activityPaymentBinding.rlPaypalView.setVisibility(View.GONE);
                activityPaymentBinding.rlBankView.setVisibility(View.GONE);
            }
                break;


            case 1: {
                activityPaymentBinding.rlCreditCardView.setVisibility(View.GONE);
                activityPaymentBinding.rlPaypalView.setVisibility(View.VISIBLE);
                activityPaymentBinding.rlBankView.setVisibility(View.GONE);
            }
                break;

            case 2: {
                activityPaymentBinding.rlCreditCardView.setVisibility(View.GONE);
                activityPaymentBinding.rlPaypalView.setVisibility(View.GONE);
                activityPaymentBinding.rlBankView.setVisibility(View.VISIBLE);
            }
                break;
        }
    }

    public void createPayment(){
        paymentViewModel.createPayment(
                 "paypal",
                "24356475",
                "credit",
                "2019",
                "12",
                "987",
               "BINIYAM ABERA",
                "Gr."

        ).observe(this,paymentOrExp -> {
            if (paymentOrExp.data != null) {
                Toast.makeText(this, "Payment Created successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Something is off", Toast.LENGTH_LONG).show();
            }
        });
    }
}