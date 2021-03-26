package com.example.betterworld.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.SplashRepository;

import javax.inject.Inject;

public class SplashViewModel {
    private SplashRepository _splashRepository;
    public LiveData<DataOrException<User, Exception>> userLiveData;

    @Inject
    SplashViewModel(SplashRepository splashRepository) {
        this._splashRepository = splashRepository;
    }

    public boolean checkIfUserIsAuthenticated() {
        return _splashRepository.checkIfUserIsAuthenticatedInFirebase();
    }

    public String getUid() {
        return _splashRepository.getFirebaseUserUid();
    }

}
