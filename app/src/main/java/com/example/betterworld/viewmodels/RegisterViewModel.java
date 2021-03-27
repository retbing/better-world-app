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

    public LiveData<DataOrException<User, Exception>> createdUserLiveData;

    @Inject
    RegisterViewModel(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }


    public void createUser(User user) {

    }

    public void createNewAuthUser(String email, String password) {
        createdUserLiveData = registerRepository.createUserInFirestore( email, password);
    }
}
