package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityCharityDetailsBinding;
import com.example.betterworld.viewmodels.CharityViewModel;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToDonationDetailActivity;

@AndroidEntryPoint
public class CharityDetailsActivity extends AppCompatActivity {


    @Inject
    CharityViewModel charityDetailsViewModel;
    private ActivityCharityDetailsBinding activityCharityDetailsBinding;
    String thisCharity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCharityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_charity_details);
        Intent intent = getIntent();
        thisCharity = intent.getStringExtra("CHARITY_ID");
        Toast.makeText(CharityDetailsActivity.this, String.format("Charity Id:%s",thisCharity), Toast.LENGTH_SHORT).show();

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

        charityDetailsViewModel.getCharityByID(thisCharity).observe(this, dataOrExp -> {
            if (dataOrExp.data != null) {

         Glide.with(CharityDetailsActivity.this).load(dataOrExp.data.getImageUrl()).into(activityCharityDetailsBinding.imageView2);
                activityCharityDetailsBinding.textView11.setText((String.valueOf(dataOrExp.data.getTarget())));
                activityCharityDetailsBinding.textView13.setText(String.valueOf(dataOrExp.data.getTarget()));
                activityCharityDetailsBinding.textView14.setText( dataOrExp.data.getDescription());
                activityCharityDetailsBinding.textView9.setText( getDateDiff(new Date(),dataOrExp.data.getDueDate()) +" days left");
                activityCharityDetailsBinding.textView7.setText(dataOrExp.data.getTitle());

                charityDetailsViewModel.getUserByID(dataOrExp.data.getUserId()).observe(CharityDetailsActivity.this,dataOrExc->{
                    if (dataOrExc.data != null) {
                        activityCharityDetailsBinding.textView8.setText("By :"+dataOrExc.data.getUsername());
                    }else{
                        activityCharityDetailsBinding.textView8.setText("By : Unknown User");
                    }
                });
            }
            else{
                Toast.makeText(CharityDetailsActivity.this, "Data doesn't Found", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public  String getDateDiff(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long diffTime = endTime - startTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);
        DateFormat dateFormat = DateFormat.getDateInstance();
        return String.valueOf(diffDays);
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