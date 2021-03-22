package com.example.betterworld.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.example.betterworld.utils.Constants.USERS_REF;

@Singleton
public class SplashRepository {
    private FirebaseAuth _firebaseAuth;
    private CollectionReference _usersRef;

    @Inject
    public SplashRepository(FirebaseAuth _firebaseAuth, @Named(USERS_REF) CollectionReference _usersRef) {
        this._firebaseAuth = _firebaseAuth;
        this._usersRef = _usersRef;
    }
}
