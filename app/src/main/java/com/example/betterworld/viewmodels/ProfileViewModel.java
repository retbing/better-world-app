package com.example.betterworld.viewmodels;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.AuthRepository;
import com.example.betterworld.repositories.ProfileRepository;
import com.example.betterworld.repositories.SplashRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private SplashRepository _splashRepository;
    private AuthRepository _authRepository;
    private ProfileRepository _profileRepository;

    @Inject
    ProfileViewModel(SplashRepository splashRepository, AuthRepository authRepository,ProfileRepository _profileRepository) {
        this._splashRepository = splashRepository;
        this._authRepository = authRepository;
        this._profileRepository = _profileRepository;
    }

    public FirebaseUser checkIfUserIsAuthenticated() {
        return _splashRepository.checkIfUserIsAuthenticatedInFirebase();
    }

    public MutableLiveData<DataOrException<User, Exception>> getUserFromFirestore(String uuid, String email) {
        return  _authRepository.getUserFromFirestore(uuid, email);
    }
    public MutableLiveData<DataOrException<Integer, Exception>> editUserInFireStore(String uuid, Map<String, Object> userMap) {
        return  _authRepository.updateAuthUser(uuid, userMap);
    }


    public MutableLiveData<DataOrException<String, Exception>> uploadImage(Uri uri) {
        return _profileRepository.uploadImageToFirebaseStorage(uri);
    }

    public void logOut() {
        _authRepository.signOut();
    }
}
