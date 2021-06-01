package com.example.betterworld.viewmodels;

import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class HomeViewModel {
    private AuthRepository _authRepository;
    private SplashViewModel _spaSplashViewModel;

    @Inject
    public HomeViewModel(AuthRepository _authRepository,SplashViewModel _spaSplashViewModel) {
        this._authRepository = _authRepository;
        this._spaSplashViewModel = _spaSplashViewModel;
    }

    public FirebaseUser checkIfUserIsAuthenticated() {
        return _spaSplashViewModel.checkIfUserIsAuthenticated();
    }
    public MutableLiveData<DataOrException<User, Exception>> getUserFromFirestore(String uuid, String email) {
        return  _authRepository.getUserFromFirestore(uuid, email);
    }
}
