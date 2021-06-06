package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityCharityDetailsBinding;
import com.example.betterworld.models.Charity;
import com.example.betterworld.viewmodels.CharityViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToDonationDetailActivity;

@AndroidEntryPoint
public class CharityDetailsActivity extends AppCompatActivity {


    @Inject
    CharityViewModel charityDetailsViewModel;
    private ActivityCharityDetailsBinding activityCharityDetailsBinding;
    String charityId;
    Charity charity;
    private float amountOfDonation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCharityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_charity_details);
        Intent intent = getIntent();
        charityId = intent.getStringExtra("CHARITY_ID");
        _initComponents();
    }

    private void _initComponents() {
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
        activityCharityDetailsBinding.btnDonate.setOnClickListener(view -> {
            if(charity != null) {
                goToDonationDetailActivity(this, amountOfDonation, charity.getCharityId(), charity.getUserId(), charity.getUserName(), charity.getImageUrl(), charity.getTitle());
            }
        });

        charityDetailsViewModel.watchCharityById(charityId).observe(this, dataOrExp -> {
            if (dataOrExp.data != null) {
                this.charity = dataOrExp.data;
                Glide.with(CharityDetailsActivity.this).load(dataOrExp.data.getImageUrl()).into(activityCharityDetailsBinding.imageView2);
                activityCharityDetailsBinding.textView11.setText((String.valueOf(dataOrExp.data.getTarget())));
                activityCharityDetailsBinding.textView13.setText(String.valueOf(dataOrExp.data.getRemaining()));
                activityCharityDetailsBinding.textView14.setText(dataOrExp.data.getDescription());
                activityCharityDetailsBinding.button.setText(dataOrExp.data.getCategoryName());
                activityCharityDetailsBinding.textView9.setText(String.valueOf(dataOrExp.data.daysLeft()) + " days left");
                activityCharityDetailsBinding.textView7.setText(dataOrExp.data.getTitle());
                activityCharityDetailsBinding.pbDonated.setProgress(dataOrExp.data.getPercentToInteger());
                activityCharityDetailsBinding.progressBar2.setProgress((int) (100 * dataOrExp.data.daysPassed() / dataOrExp.data.totalDays()));
                activityCharityDetailsBinding.textView8.setText( dataOrExp.data.getUserName());
            } else {
                Toast.makeText(CharityDetailsActivity.this, "Data doesn't Found", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void visibleNumbersFrameLayouts() {
        activityCharityDetailsBinding.frameLayoutNumbers.setVisibility(View.VISIBLE);
        activityCharityDetailsBinding.frameLayoutTransparent.setVisibility(View.VISIBLE);
    }

    private void goneNumbersFrameLayouts() {
        activityCharityDetailsBinding.frameLayoutNumbers.setVisibility(View.GONE);
        activityCharityDetailsBinding.frameLayoutTransparent.setVisibility(View.GONE);
        setTextToTextView("0");

    }

    private void setNumberToAmount(String number) {
        String amount = activityCharityDetailsBinding.tvDonationAmt.getText().toString();
        String newAmount = "" + amount;
        if (newAmount.equals("0")) {
            setTextToTextView("");
            newAmount = "";
        }

        switch (number) {
            case "1":
                newAmount += "1";
                break;
            case "2":
                newAmount += "2";
                break;
            case "3":
                newAmount += "3";
                break;
            case "4":
                newAmount += "4";
                break;
            case "5":
                newAmount += "5";
                break;
            case "6":
                newAmount += "6";
                break;
            case "7":
                newAmount += "7";
                break;
            case "8":
                newAmount += "8";
                break;
            case "9":
                newAmount += "9";
                break;
            case "0":
                newAmount += "0";
                break;
            case "x":
                int length = activityCharityDetailsBinding.tvDonationAmt.getText().length();
                if (length >= 2) {
                    newAmount = newAmount.substring(0, length - 1);
                } else {
                    newAmount = "0";
                }
                break;
        }
        setTextToTextView(newAmount);
        amountOfDonation = Float.parseFloat(newAmount);

    }

    private void setTextToTextView(String txt) {
        activityCharityDetailsBinding.tvDonationAmt.setText(txt);
    }


}