package com.example.betterworld.di.modules;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import static com.example.betterworld.utils.Constants.CHARITIES_COLLECTION;
import static com.example.betterworld.utils.Constants.CHARITIES_REF;
import static com.example.betterworld.utils.Constants.CHARITIES_STORAGE_REF;
import static com.example.betterworld.utils.Constants.NOTIFICATIONS_REF;
import static com.example.betterworld.utils.Constants.PAYMENT_METHOD_COLLECTION;
import static com.example.betterworld.utils.Constants.PAYMENT_METHOD_REF;
import static com.example.betterworld.utils.Constants.PROFILES_STORAGE_REF;
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
    public static FirebaseStorage provideFirebaseStorage() {
        return FirebaseStorage.getInstance();
    }


    @Provides
    @Singleton
    @Named(CHARITIES_STORAGE_REF)
    public static StorageReference provideCharitiesStorageReference(FirebaseStorage storage) {
        return storage.getReference().child(CHARITIES_STORAGE_REF);
    }

    @Provides
    @Singleton
    @Named(PROFILES_STORAGE_REF)
    public static StorageReference provideProfilesStorageReference(FirebaseStorage storage) {
        return storage.getReference().child(PROFILES_STORAGE_REF);
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

    @Provides
    @Singleton
    @Named(PAYMENT_METHOD_REF)
    public static CollectionReference providePaymentsCollectionReference(FirebaseFirestore rootRef) {
        return rootRef.collection(PAYMENT_METHOD_COLLECTION);
    }





}
