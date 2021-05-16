package com.example.betterworld.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.repositories.AuthRepository;

import javax.inject.Inject;

public class ForgotPasswordViewModel extends ViewModel {
    private AuthRepository authRepository;

    @Inject
    ForgotPasswordViewModel(AuthRepository forgotPasswordRepository) {
        this.authRepository = forgotPasswordRepository;
    }

    public MutableLiveData<DataOrException<Integer, Exception>> resetPassword(String email) {
        return this.authRepository.resetPassword(email);
    }
}