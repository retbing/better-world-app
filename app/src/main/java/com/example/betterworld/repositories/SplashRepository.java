package com.example.betterworld.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.example.betterworld.utils.Constants.USERS_REF;

@Singleton
public class SplashRepository {
    private FirebaseAuth _firebaseAuth;

    @Inject
    public SplashRepository(FirebaseAuth firebaseAuth) {
        this._firebaseAuth = firebaseAuth;

    }

    public FirebaseUser checkIfUserIsAuthenticatedInFirebase() {
        return _firebaseAuth.getCurrentUser();
    }

    public String getFirebaseUserUid() {
        FirebaseUser firebaseUser = _firebaseAuth.getCurrentUser();
        return firebaseUser.getUid();
    }
}
