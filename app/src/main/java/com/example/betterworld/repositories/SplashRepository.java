package com.example.betterworld.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

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
