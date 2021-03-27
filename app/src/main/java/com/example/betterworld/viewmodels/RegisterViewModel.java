package com.example.betterworld.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.RegisterRepository;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

import javax.inject.Inject;

import static com.example.betterworld.utils.HelperClass.logErrorMessage;

public class RegisterViewModel extends ViewModel {

    private RegisterRepository registerRepository;

    public LiveData<DataOrException<FirebaseUser, Exception>> createdUserLiveData;

    @Inject
    RegisterViewModel(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }


    public void createUser(String email, String password,String username) {
        createdUserLiveData = registerRepository.createUserInFirestore( email, password, username);
    }

}
