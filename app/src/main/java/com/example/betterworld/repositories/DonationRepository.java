package com.example.betterworld.repositories;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.Donation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.messaging.FirebaseMessaging;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.scopes.ActivityScoped;

import static com.example.betterworld.utils.Constants.DONATION_REF;
import static com.example.betterworld.utils.Constants.TAG;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;

@ActivityScoped
public class DonationRepository {

     CollectionReference donationCollection;
     CharityRepository charityRepository;


    @Inject
    public DonationRepository(@Named(DONATION_REF) CollectionReference charitiesRef,CharityRepository charityRepository) {
        this.donationCollection = charitiesRef;
        this.charityRepository = charityRepository;
    }

    public MutableLiveData<DataOrException<Donation, Exception>> createDonationOnFireStore(Donation donation) {
        MutableLiveData<DataOrException<Donation, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        donationCollection
                .document(donation.getDonationId())
                .set(donation.toMap())
                .addOnCompleteListener(donationTask -> {
                    DataOrException<Donation, Exception> dataOrException = new DataOrException<>();
                    if (donationTask.isSuccessful()) {
                        dataOrException.data = donation;
                        charityRepository.updateCharity(donation.getCharityId(),
                                new HashMap<String, Object>() {{
                                    put("donated", donation.getAmount());
                                }}
                                );
                        logErrorMessage("Charity has been Created");
                    } else {
                        logErrorMessage("error on");
                        dataOrException.exception = donationTask.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }

}
