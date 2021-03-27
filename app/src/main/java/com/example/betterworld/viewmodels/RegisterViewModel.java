package com.example.betterworld.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.RegisterRepository;
import com.google.firebase.auth.AuthCredential;

import java.util.Map;

import javax.inject.Inject;

import static com.example.betterworld.utils.HelperClass.logErrorMessage;

public class RegisterViewModel extends ViewModel {

    private RegisterRepository registerRepository;
    LiveData<DataOrException<User, Exception>> authenticatedUserLiveData;
    LiveData<DataOrException<User, Exception>> createdUserLiveData;

    @Inject
    RegisterViewModel(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public void signInWithGoogle(AuthCredential googleAuthCredential) {
//        authenticatedUserLiveData = registerRepository.firebaseSignInWithGoogle(googleAuthCredential);
    }

    public void createUser(String email, String password,String username) {
//        createdUserLiveData = registerRepository.createUserInFirestore( email, password, username);
        logErrorMessage("Here ate createUser Function");
        registerRepository.createUserInFirestore( email, password, username);
    }

}
