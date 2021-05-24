package com.example.betterworld.viewmodels;

import com.example.betterworld.models.User;
import com.example.betterworld.repositories.AuthRepository;

import javax.inject.Inject;

public class HomeViewModel {
    private AuthRepository _authRepository;

    @Inject
    public HomeViewModel(AuthRepository _authRepository) {
        this._authRepository = _authRepository;
    }

    public User getUser() {
        return this._authRepository.getUser();
    }
}
