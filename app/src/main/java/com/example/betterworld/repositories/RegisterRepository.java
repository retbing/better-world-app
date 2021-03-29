package com.example.betterworld.repositories;


import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.example.betterworld.utils.Constants.USERS_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

@Singleton
public class RegisterRepository {
    private FirebaseAuth auth;
    private CollectionReference db;

    @Inject
    RegisterRepository(FirebaseAuth auth, @Named(USERS_REF) CollectionReference db) {
        this.auth = auth;
        this.db = db;
    }


    public MutableLiveData<DataOrException<User, Exception>> createUserInFirestore(User authenticatedUser) {
        MutableLiveData<DataOrException<User, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        // Add a new document with a generated ID
                db
                .add(authenticatedUser)
                .addOnCompleteListener(userCreationTask -> {
                    DataOrException<User, Exception> dataOrException = new DataOrException<>();
                    if (userCreationTask.isSuccessful()) {
                        dataOrException.data = authenticatedUser;
                        logErrorMessage("User Have been Created createUserInFirestore");
                    } else {
                        logErrorMessage("error on createAuthUserInFirestore");
                        dataOrException.exception = userCreationTask.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }

    public MutableLiveData<DataOrException<FirebaseUser, Exception>> createAuthUserInFirestore(String email, String password) {
        MutableLiveData<DataOrException<FirebaseUser, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            DataOrException<FirebaseUser, Exception> dataOrException = new DataOrException<>();
            if (task.isSuccessful()) {
                FirebaseUser user = auth.getCurrentUser();
                dataOrException.data = user;
                logErrorMessage("User Have been Created createAuthUserInFirestore");

            } else {
                dataOrException.exception = task.getException();
                logErrorMessage("User creation Unsuccessful createAuthUserInFirestore");
            }
            dataOrExceptionMutableLiveData.setValue(dataOrException);
        });
        return dataOrExceptionMutableLiveData;
    }
}
