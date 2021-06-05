package com.example.betterworld.viewmodels;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.Donation;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.AuthRepository;
import com.example.betterworld.repositories.CharityRepository;
import com.example.betterworld.repositories.DonationRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

public class DonationViewModel extends ViewModel {


    private DonationRepository _donationRepository;
    private AuthRepository _authRepository;

    private static final String TAG = "CharityViewModel";

    @Inject
    public DonationViewModel(DonationRepository _donationRepository, AuthRepository authRepository) {
        this._donationRepository = _donationRepository;
        this._authRepository = authRepository;
    }

    public MutableLiveData<DataOrException<Donation, Exception>> createDonation(
            String donationId, String userImageUrl, String charityId, Float amount,Boolean isAnonymous) {
        if (_authRepository.getUser() != null) {

            final String username = _authRepository.getUser().getUsername();
            final String userId = _authRepository.getUser().getUserId();


            Donation donation = new Donation(donationId,userId,username,userImageUrl,charityId,amount,isAnonymous);

            return _donationRepository.createDonationOnFireStore(donation);

        } else {
            Log.d(TAG, "createCharity: " + "logout the user!! user is null");
            return null;
        }
    }

}
