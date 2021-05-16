package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityCharityDetailsBinding;

import static com.example.betterworld.utils.Actions.goToDonationDetailActivity;

public class CharityDetailsActivity extends AppCompatActivity {


    private ActivityCharityDetailsBinding activityCharityDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCharityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_charity_details);
        _initComponents();
    }

    private void _initComponents(){
        activityCharityDetailsBinding.tvDonationAmt.setText("0");
        activityCharityDetailsBinding.btnJoin.setOnClickListener(view -> visibleNumbersFrameLayouts());
        activityCharityDetailsBinding.btnClose.setOnClickListener(view -> goneNumbersFrameLayouts());
        activityCharityDetailsBinding.btn1.setOnClickListener(view -> setNumberToAmount("1"));
        activityCharityDetailsBinding.btn2.setOnClickListener(view -> setNumberToAmount("2"));
        activityCharityDetailsBinding.btn3.setOnClickListener(view -> setNumberToAmount("3"));
        activityCharityDetailsBinding.btn4.setOnClickListener(view -> setNumberToAmount("4"));
        activityCharityDetailsBinding.btn5.setOnClickListener(view -> setNumberToAmount("5"));
        activityCharityDetailsBinding.btn6.setOnClickListener(view -> setNumberToAmount("6"));
        activityCharityDetailsBinding.btn7.setOnClickListener(view -> setNumberToAmount("7"));
        activityCharityDetailsBinding.btn8.setOnClickListener(view -> setNumberToAmount("8"));
        activityCharityDetailsBinding.btn9.setOnClickListener(view -> setNumberToAmount("9"));
        activityCharityDetailsBinding.btn0.setOnClickListener(view -> setNumberToAmount("0"));
        activityCharityDetailsBinding.btnDelete.setOnClickListener(view -> setNumberToAmount("x"));
        activityCharityDetailsBinding.btnDonate.setOnClickListener(view -> goToDonationDetailActivity(this));

    }

    private void visibleNumbersFrameLayouts(){
        activityCharityDetailsBinding.frameLayoutNumbers.setVisibility(View.VISIBLE);
        activityCharityDetailsBinding.frameLayoutTransparent.setVisibility(View.VISIBLE);
    }

    private void goneNumbersFrameLayouts(){
        activityCharityDetailsBinding.frameLayoutNumbers.setVisibility(View.GONE);
        activityCharityDetailsBinding.frameLayoutTransparent.setVisibility(View.GONE);
    }

    private void setNumberToAmount(String number){
        String amount = activityCharityDetailsBinding.tvDonationAmt.getText().toString();
        if(activityCharityDetailsBinding.tvDonationAmt.getText().toString().equals("0")){
            setTextToTextView(" ");
        }

        switch (number){
            case "1":
                amount += "1";
                setTextToTextView(amount);
                break;
            case "2":
                amount += "2";
                setTextToTextView(amount);
                break;
            case "3":
                amount += "3";
                setTextToTextView(amount);
                break;
            case "4":
                amount += "4";
                setTextToTextView(amount);
                break;
            case "5":
                amount += "5";
                setTextToTextView(amount);
                break;
            case "6":
                amount += "6";
                setTextToTextView(amount);
                break;
            case "7":
                amount += "7";
                setTextToTextView(amount);
                break;
            case "8":
                amount += "8";
                setTextToTextView(amount);
                break;
            case "9":
                amount += "9";
                setTextToTextView(amount);
                break;
            case "0":
                if(!activityCharityDetailsBinding.tvDonationAmt.getText().toString().equals("0")){
                    amount += "0";
                    setTextToTextView(amount);
                }
                break;
            case "x":
                int length = activityCharityDetailsBinding.tvDonationAmt.getText().length();
                if(length > 2){
                    amount = amount.substring(0,length-1);
                    setTextToTextView(amount);
                }
                break;
        }

    }

    private void setTextToTextView(String amount){
        activityCharityDetailsBinding.tvDonationAmt.setText(amount);
    }



}