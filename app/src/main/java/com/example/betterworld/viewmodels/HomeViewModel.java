package com.example.betterworld.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.AuthRepository;
import com.example.betterworld.repositories.HomeRepository;
import com.example.betterworld.repositories.NotificationRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import static com.example.betterworld.utils.Constants.TAG;

public class HomeViewModel {
    private AuthRepository _authRepository;
    private SplashViewModel _spaSplashViewModel;
    private HomeRepository _homeRepository;
    private NotificationRepository _notificationRepository;

    public  MutableLiveData<DataOrException<User, Exception>> firestoreUserMutableLiveData;

    @Inject
    public HomeViewModel(AuthRepository _authRepository,SplashViewModel _spaSplashViewModel,HomeRepository _homeRepository, NotificationRepository _notificationRepository) {
        this._authRepository = _authRepository;
        this._spaSplashViewModel = _spaSplashViewModel;
        this._homeRepository = _homeRepository;
        this._notificationRepository = _notificationRepository;
    }

    public FirebaseUser checkIfUserIsAuthenticated() {
        return _spaSplashViewModel.checkIfUserIsAuthenticated();
    }
    public void getUserFromFirestore(String uuid, String email) {
        firestoreUserMutableLiveData =  _authRepository.getUserFromFirestore(uuid, email);
    }
    public void subscribeToDonation(){

        _homeRepository.subScribeToTopic();
    }



}
