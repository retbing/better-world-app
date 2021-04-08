package com.example.betterworld.di.modules;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import static com.example.betterworld.utils.Constants.CHARITIES_COLLECTION;
import static com.example.betterworld.utils.Constants.CHARITIES_REF;
import static com.example.betterworld.utils.Constants.NOTIFICATIONS_REF;
import static com.example.betterworld.utils.Constants.USERS_COLLECTION;
import static com.example.betterworld.utils.Constants.USERS_REF;

@Module
@InstallIn(SingletonComponent.class)
public class FirebaseModule {

    @Provides
    @Singleton
    public static FirebaseAuth provideFirebaseAuthInstance() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    public static FirebaseFirestore provideFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }

    @Provides
    @Singleton
    @Named(USERS_REF)
    public static CollectionReference provideUsersCollectionReference(FirebaseFirestore rootRef) {
        return rootRef.collection(USERS_COLLECTION);
    }

    @Provides
    @Singleton
    @Named(CHARITIES_REF)
    public static CollectionReference provideCharitiesCollectionReference(FirebaseFirestore rootRef) {
        return rootRef.collection(CHARITIES_COLLECTION);
    }



}
