package com.example.betterworld.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.repositories.AuthRepository;
import com.example.betterworld.repositories.CharityRepository;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

public class CharityViewModel extends ViewModel {

    private CharityRepository _charityRepository;
    private AuthRepository _authRepository;
    private static final String TAG = "CharityViewModel";

    @Inject
    public CharityViewModel(CharityRepository charityRepository, AuthRepository authRepository) {

        this._charityRepository = charityRepository;
        this._authRepository = authRepository;
    }


    public MutableLiveData<DataOrException<Charity, Exception>> createCharity(String title, String whoBenefits, String description, float target, Date startDate, Date dueDate) {

        if (_authRepository.getUser() != null) {

            final String username = _authRepository.getUser().getUsername();
            final String userId = _authRepository.getUser().getUserId();

            Charity charity = new Charity(UUID.randomUUID().toString(), title,
                    description, whoBenefits, "", target, 0, startDate.getTime(), dueDate.getTime(),
                    "Health", "health-12345", userId, username
            );

            return _charityRepository.createCharityOnFireStore(charity);

        } else {
            Log.d(TAG, "createCharity: " + "logout the user!! user is null");
            return null;
        }
    }
}
