package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityDonationDetailBinding;
import com.example.betterworld.viewmodels.DonationViewModel;
import com.example.betterworld.viewmodels.NotificationViewModel;

import java.util.UUID;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToCharityDetailsActivity;
import static com.example.betterworld.utils.Actions.goToCharityFormActivity;
import static com.example.betterworld.utils.Actions.gotoMainActivity;

@AndroidEntryPoint
public class DonationDetailActivity extends AppCompatActivity {
    ActivityDonationDetailBinding activityDonationDetailBinding;
    @Inject
    DonationViewModel donationViewModel;
    @Inject
    NotificationViewModel notificationViewModel;

    int bg_btn_selected, bg_btn_unselected;
    int textColorLightGrey,textColorWhite;
    Drawable bg_tv_selected, bg_tv_unselected;
    TextView tvTransfer, tvPaypal, tvCreditCard;
    AppCompatImageButton btnTransfer, btnPaypal, btnCreditCard, btnReturnPage;
    AppCompatButton btnHome, btnDonate,btnAnonymousDonate;
    FrameLayout flSuccessful,flTransparent;
    Float amountOfDonation;
    String CharityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDonationDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_donation_detail);
        amountOfDonation =getIntent().getFloatExtra("DONATION_AMOUNT",0);
        CharityID =  getIntent().getStringExtra("CHARITY_ID");
        _initComponents();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    void _initComponents(){
        bg_btn_selected = R.drawable.ic_circle;
        bg_btn_unselected = R.drawable.ic_empty_circle;
        bg_tv_selected = getResources().getDrawable(R.drawable.button_bg_credit_cards_selected);
        bg_tv_unselected = getResources().getDrawable(R.drawable.button_bg_credit_cards_unselected);
        textColorLightGrey = getResources().getColor(R.color.bw_light_grey_2);
        textColorWhite = getResources().getColor(R.color.white);

        btnCreditCard = activityDonationDetailBinding.btnCreditCard;
        btnPaypal = activityDonationDetailBinding.btnPaypal;
        btnTransfer = activityDonationDetailBinding.btnBankTransfer;
        btnReturnPage = activityDonationDetailBinding.btnReturnPage;
        btnDonate = activityDonationDetailBinding.btnDonate;
        btnAnonymousDonate = activityDonationDetailBinding.btnAonymousDonate;
        btnHome = activityDonationDetailBinding.btnHome;
        activityDonationDetailBinding.tvTotalDonate.setText("$ "+String.valueOf(amountOfDonation));

        flSuccessful = activityDonationDetailBinding.frameLayoutSuccess;
        flTransparent = activityDonationDetailBinding.frameLayoutTransparent;

        tvTransfer = activityDonationDetailBinding.tvBankTransfer;
        tvPaypal = activityDonationDetailBinding.tvPaypal;
        tvCreditCard = activityDonationDetailBinding.tvCreditCard;


        btnTransfer.setOnClickListener(view -> clickPaymentButtons("Bank Transfer"));
        btnPaypal.setOnClickListener(view -> clickPaymentButtons("Paypal"));
        btnCreditCard.setOnClickListener(view -> clickPaymentButtons("Credit Card"));

        //TODO: Return Charity Detail Page
        //btnReturnPage.setOnClickListener(view -> goToCharityDetailsActivity(this, getIntent().getStringExtra("CHARITY_ID")));

        btnHome.setOnClickListener(view -> gotoMainActivity(this));
        btnDonate.setOnClickListener(view -> checkDonationClearly(false));
        btnAnonymousDonate.setOnClickListener(view -> checkDonationClearly(true));
    }


    void clickPaymentButtons(String paymentMethod){
        switch (paymentMethod){
            case "Credit Card":
                btnCreditCard.setImageResource(R.drawable.ic_circle);
                tvCreditCard.setBackground(bg_tv_selected);
                tvCreditCard.setTextColor(textColorWhite);

                btnTransfer.setImageResource(bg_btn_unselected);
                tvTransfer.setBackground(bg_tv_unselected);
                tvTransfer.setTextColor(textColorLightGrey);

                btnPaypal.setImageResource(bg_btn_unselected);
                tvPaypal.setBackground(bg_tv_unselected);
                tvPaypal.setTextColor(textColorLightGrey);

                break;

            case "Bank Transfer":
                btnTransfer.setImageResource(bg_btn_selected);
                tvTransfer.setBackground(bg_tv_selected);
                tvTransfer.setTextColor(textColorWhite);

                btnCreditCard.setImageResource(bg_btn_unselected);
                tvCreditCard.setBackground(bg_tv_unselected);
                tvCreditCard.setTextColor(textColorLightGrey);

                btnPaypal.setImageResource(bg_btn_unselected);
                tvPaypal.setBackground(bg_tv_unselected);
                tvPaypal.setTextColor(textColorLightGrey);
                break;


            case "Paypal":
                btnPaypal.setImageResource(bg_btn_selected);
                tvPaypal.setBackground(bg_tv_selected);
                tvPaypal.setTextColor(textColorWhite);

                btnCreditCard.setImageResource(bg_btn_unselected);
                tvCreditCard.setBackground(bg_tv_unselected);
                tvCreditCard.setTextColor(textColorLightGrey);

                btnTransfer.setImageResource(bg_btn_unselected);
                tvTransfer.setBackground(bg_tv_unselected);
                tvTransfer.setTextColor(textColorLightGrey);

                break;
        }
    }

    void checkDonationClearly(Boolean isAnonymous){
        //TODO: Add donation control and save database
        donationViewModel.createDonation(UUID.randomUUID().toString(),"",CharityID,amountOfDonation,isAnonymous)
                .observe(this,dataOrExc->{
                    if (dataOrExc != null){
                        notificationViewModel.createNotification(UUID.randomUUID().toString(),CharityID,"$ "+amountOfDonation +" have been donated")
                                .observe(this,dataOrException->{
                                    if (dataOrException != null){
                                        flTransparent.setVisibility(View.VISIBLE);
                                        flSuccessful.setVisibility(View.VISIBLE);
                                    }
                                    else {
                                        Toast.makeText(this, "notification not sent!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else {
                        Toast.makeText(this, "Donation unsucessfull!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}