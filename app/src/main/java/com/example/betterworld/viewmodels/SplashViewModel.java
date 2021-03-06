package com.example.betterworld.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.AuthRepository;
import com.example.betterworld.repositories.SplashRepository;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class SplashViewModel extends ViewModel {
    private SplashRepository _splashRepository;
    private AuthRepository _authRepository;

    @Inject
    SplashViewModel(SplashRepository splashRepository, AuthRepository authRepository) {
        this._splashRepository = splashRepository;
        this._authRepository = authRepository;
    }

    public FirebaseUser checkIfUserIsAuthenticated() {
        return _splashRepository.checkIfUserIsAuthenticatedInFirebase();
    }

    public MutableLiveData<DataOrException<User, Exception>> getUserFromFirestore(String uuid, String email) {
        return  _authRepository.getUserFromFirestore(uuid, email);
    }

    public String getUid() {
        return _splashRepository.getFirebaseUserUid();
    }

}
