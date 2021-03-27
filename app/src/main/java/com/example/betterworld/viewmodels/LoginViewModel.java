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
    private LoginRepository registerRepository;
    LiveData<DataOrException<User, Exception>> authenticatedUserLiveData;
    LiveData<DataOrException<User, Exception>> createdUserLiveData;

    @Inject
    LoginViewModel(LoginRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    void signInWithGoogle(AuthCredential googleAuthCredential) {
        authenticatedUserLiveData = registerRepository.firebaseSignInWithGoogle(googleAuthCredential);
    }

    void createUser(User authenticatedUser) {
        createdUserLiveData = registerRepository.createUserInFirestore(authenticatedUser);
    }

}
