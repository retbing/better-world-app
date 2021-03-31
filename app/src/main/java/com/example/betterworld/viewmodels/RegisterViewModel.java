package com.example.betterworld.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.RegisterRepository;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    private RegisterRepository registerRepository;

    public LiveData<DataOrException<FirebaseUser, Exception>> createdAuthUserLiveData;
    public LiveData<DataOrException<User, Exception>> createdUserLiveData;

    @Inject
    RegisterViewModel(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }


    public void createUser(User user) {

        createdUserLiveData = registerRepository.createUserInFirestore(user);
    }

    public void createNewAuthUser(String email, String password) {
        createdAuthUserLiveData = registerRepository.createAuthUserInFirebase(email, password);
    }
}
