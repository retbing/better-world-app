package com.example.betterworld.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.LoginRepository;
import com.example.betterworld.repositories.SplashRepository;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class SplashViewModel {
    private SplashRepository _splashRepository;
    private LoginRepository _loginRepository;

    @Inject
    SplashViewModel(SplashRepository splashRepository, LoginRepository loginRepository) {
        this._splashRepository = splashRepository;
        this._loginRepository = loginRepository;
    }

    public FirebaseUser checkIfUserIsAuthenticated() {
        return _splashRepository.checkIfUserIsAuthenticatedInFirebase();
    }

    public MutableLiveData<DataOrException<User, Exception>> getUserFromFirestore(String uuid, String email) {
        return  _loginRepository.getUserFromFirestore(uuid, email);
    }

    public String getUid() {
        return _splashRepository.getFirebaseUserUid();
    }

}
