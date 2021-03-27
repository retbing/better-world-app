package com.example.betterworld.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.LoginRepository;
import com.example.betterworld.repositories.RegisterRepository;
import com.google.firebase.auth.AuthCredential;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    private LoginRepository loginRepository;
    LiveData<DataOrException<User, Exception>> authenticatedUserLiveData;

    @Inject
    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    void signInWithGoogle(AuthCredential googleAuthCredential) {
        authenticatedUserLiveData = loginRepository.firebaseSignInWithGoogle(googleAuthCredential);
    }


}
